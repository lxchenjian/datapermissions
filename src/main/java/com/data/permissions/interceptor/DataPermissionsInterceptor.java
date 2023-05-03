package com.data.permissions.interceptor;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.data.permissions.annotation.DataPermission;
import com.data.permissions.bean.DO.UserDO;
import com.data.permissions.bean.DO.UserDataPermission;
import com.data.permissions.util.ClassUtil;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.catalina.session.StoreBase;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
//@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class DataPermissionsInterceptor implements Interceptor {

    // 是否拦截的参数
    // 扫描范围
    @Value("jc.dataPermissions.packagePath")
    private String packagePath;


    // 存代码里面不好维护
    //private final static String DEPT_ID = "dept_id";
    //private final static String USER_ID = "create_user";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 0、获取用户权限
        List<UserDataPermission> userDataPermissions = new ArrayList<>();
        List<String> accDateValue = new ArrayList<>();
        accDateValue.add("2023-02-02");
        accDateValue.add("2023-03-03");
        userDataPermissions.add(new UserDataPermission("acc_date",accDateValue));

        List<String> storeValue = new ArrayList<>();
        storeValue.add("819");
        storeValue.add("820");
        userDataPermissions.add(new UserDataPermission("store_code",storeValue));

        UserDO user = new UserDO(1L,"小民",userDataPermissions);

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
        boolean isPermi = false;
        String value = "";
        Class<?> clazz = Class.forName(newId);
        //遍历方法
        for (Method method : clazz.getDeclaredMethods()) {
            //方法是否含有DataPermission注解，如果含有注解则将数据结果过滤
            if (method.isAnnotationPresent(DataPermission.class) && newName.equals(method.getName())) {
                DataPermission dataPermission =  method.getAnnotation(DataPermission.class);
                if (dataPermission != null) {
                    //不验证
                    if (!dataPermission.openOrClose()) {
                        isPermi = false;
                    } else { //开启验证
                        isPermi = true;
                    }
                    value = dataPermission.value();
                }
            }
        }


        if (isPermi){
            // 获取到原始sql语句
            String sql = statementHandler.getBoundSql().getSql();
            // 解析并返回新的SQL语句，只处理查询sql
            if (mappedStatement.getSqlCommandType().toString().equals("SELECT")) {
                sql = getSql(sql,deptIds,value,user.getId());
                System.out.println(sql);
            }
            // 修改sql
            metaObject.setValue("delegate.boundSql.sql", sql);
        }
        return invocation.proceed();

    }

    /**
     * 解析SQL语句，并返回新的SQL语句
     * 注意，该方法使用了JSqlParser来操作SQL，该依赖包Mybatis-plus已经集成了。如果要单独使用，请先自行导入依赖
     *
     * @param sql 原SQL
     * @return 新SQL
     */
    private static String getSql(String sql,List<UserDataPermission> userDataPermissions,String value,Long userId) {
        // deptIds 和 value 求并集
        List<UserDataPermission> list = userDataPermissions.stream().filter(o->value.contains(o.getFieldName())).collect(Collectors.toList());

        try {
            List<String> conditions = new ArrayList<>();
            for(UserDataPermission userDataPermission:list){
                String permissionSql = "(";
                String fieldName = userDataPermission.getFieldName();
                List<String>  values = userDataPermission.getValues();

                if (values.size() > 0) {
                    for (String deptId : values) {
                        if ("(".equals(permissionSql)) {
                            permissionSql = permissionSql + deptId;
                        } else {
                            permissionSql = permissionSql + "," + deptId;
                        }
                    }
                    permissionSql = permissionSql + ")";
                    // 修改原语句
                    conditions.add(fieldName + " in " + permissionSql);
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
            return sql;
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