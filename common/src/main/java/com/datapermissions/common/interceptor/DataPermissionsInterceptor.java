package com.datapermissions.common.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.datapermissions.common.annotation.DataPermission;
import com.datapermissions.common.bean.DO.UserDO;
import com.datapermissions.common.bean.DO.UserDataPermission;
import com.datapermissions.common.util.ThreadLocalUtil;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;


@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
//@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class DataPermissionsInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 0、获取用户权限
        List<UserDataPermission> userDataPermissions = new ArrayList<>();
        List<String> accDateValue = new ArrayList<>();
        userDataPermissions.add(new UserDataPermission("acc_date",1,"2023-02-02,2023-03-03"));

        List<String> storeValue = new ArrayList<>();
        userDataPermissions.add(new UserDataPermission("store_code",1,"819,820"));

//        List<UserDataPermission> userDataPermissions = dataMapper.getDataPermission();
        UserDO user = new UserDO(new Long(1L),"小民",userDataPermissions);

        if (user == null){
            return invocation.proceed();
        }
        List<UserDataPermission> deptIds = user.getDataPermission();
        if (deptIds == null){
            deptIds = new ArrayList<>();
        }

        // 拦截所有sql，打注解的sql需要添加数据拦截
        // 劣势 原生的拦截不了


        // 2、拿到mybatis的一些对象
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");


        // 3、判断是不是需要拦截的对象
        // mappedStatement.getId()为执行的mapper方法的全路径名,newId为执行的mapper方法的类全名
        String newId = mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf("."));
        String newName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1, mappedStatement.getId().length());
        // 获取注解的参数值 判断是否数据拦截
        String value = ThreadLocalUtil.getDataPermissions();
        Class<?> clazz = Class.forName(newId);
        //遍历方法
        for (Method method : clazz.getDeclaredMethods()) {
            //方法是否含有DataPermission注解，如果含有注解则将数据结果过滤
            if (method.isAnnotationPresent(DataPermission.class) && newName.equals(method.getName())) {
                DataPermission dataPermission =  method.getAnnotation(DataPermission.class);
                if (dataPermission != null) {
                    if(value !="" && dataPermission.value() !="") {value +="";}
                    value += dataPermission.value();
                }
            }
        }
        ThreadLocalUtil.setDataPermissions(value);
        // 加一个注解

        // 获取到原始sql语句
        String sql = statementHandler.getBoundSql().getSql();
        // 解析并返回新的SQL语句，只处理查询sql
        if (mappedStatement.getSqlCommandType().toString().equals("SELECT")) {
            sql = getSql(sql,deptIds,user.getId());
            System.out.println(sql);
        }
        // 修改sql
        metaObject.setValue("delegate.boundSql.sql", sql);
        return invocation.proceed();
    }

    /**
     * 解析SQL语句，并返回新的SQL语句
     * 注意，该方法使用了JSqlParser来操作SQL，该依赖包Mybatis-plus已经集成了。如果要单独使用，请先自行导入依赖
     *
     * @param sql 原SQL
     * @return 新SQL
     */
    private static String getSql(String sql,List<UserDataPermission> userDataPermissions,Long userId) {
        // deptIds 和 value 求交集
        List<UserDataPermission> list = userDataPermissions.stream().filter(o->ThreadLocalUtil.getDataPermissions().contains(o.getFieldName())).collect(Collectors.toList());

        List<String> dataPermissionsFiled = Arrays.stream(ThreadLocalUtil.getDataPermissions().split(",")).collect(Collectors.toList());
        // 需要替换为有别名的字段
        for(UserDataPermission userDataPermission:list){
            for(String filedName:dataPermissionsFiled){
                if( filedName.contains(userDataPermission.getFieldName()) ){
                    userDataPermission.setFieldName(filedName);
                    break;
                }
            }
        }

        try {
            List<String> conditions = new ArrayList<>();
            for(UserDataPermission userDataPermission:list){
                String fieldName = userDataPermission.getFieldName();
                int type = userDataPermission.getPermissionType();
                String[] values = userDataPermission.getValues().split(",");
                String condition = getConditions(fieldName,type,values);
                if(condition != ""){
                    conditions.add(condition);
                }
            }
            if (conditions.size() == 0) {
                return sql;
            }
            // 开始拼接
            Select select = (Select) CCJSqlParserUtil.parse(sql);
            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();

            //增加新的where条件

            for(String condition  :conditions){
                //取得最新的where
                final Expression expression = plainSelect.getWhere();

                final Expression envCondition = CCJSqlParserUtil.parseCondExpression(condition);
                if (expression == null) {
                    plainSelect.setWhere(envCondition);
                } else {
                    AndExpression andExpression = new AndExpression(expression, envCondition);
                    plainSelect.setWhere(andExpression);
                }
            }
            return plainSelect.toString();

        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getConditions(String fieldName,int type,String[] values){
        switch (type){
            case 1:
                    {
                        String permissionSql = "(";
                        if (values.length > 0) {
                            for (String deptId : values) {
                                if ("(".equals(permissionSql)) {
                                    permissionSql = permissionSql + deptId;
                                } else {
                                    permissionSql = permissionSql + "," + deptId;
                                }
                            }
                            permissionSql = permissionSql + ")";
                            // 修改原语句
                            return fieldName + " in " + permissionSql;
                        }
                    }
            default :
                return "";
        }
    }





    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
