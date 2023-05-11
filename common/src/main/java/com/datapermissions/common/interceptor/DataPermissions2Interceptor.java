package com.datapermissions.common.interceptor;

import com.datapermissions.common.annotation.DataPermissionOnClass;
import com.datapermissions.common.annotation.DataPermissionOnField;
import com.datapermissions.common.annotation.DisableDataPermission;
import com.datapermissions.common.bean.DO.DataPermission;
import com.datapermissions.common.util.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

@Component
public class DataPermissions2Interceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod=(HandlerMethod)handler;

            DataPermission value = new DataPermission();
            DisableDataPermission dataPermissionOnController =handlerMethod.getMethod().getAnnotation(DisableDataPermission.class);
            if(dataPermissionOnController!=null){
                value.setWhetherToIntercept(false);
            }

            Class[] methodParams = handlerMethod.getMethod().getParameterTypes();
            if(methodParams.length == 0){
                return super.preHandle(request, response, handler);
            }

            String interceptFields = value.getInterceptFields();
            for(Class methodParam:methodParams){
                if(methodParam.getAnnotation(DataPermissionOnClass.class) != null){
                    String temp = ((DataPermissionOnClass)methodParam.getAnnotation(DataPermissionOnClass.class)).value();
                    if(temp != ""){
                        if(interceptFields!=""){
                            interceptFields+=",";
                        }
                        interceptFields+=temp;
                    }
                    // 最后是否拦截，存在false，就不拦截
                }

                Field[] fields = methodParam.getDeclaredFields();
                for(Field field:fields){
                    if( field.getAnnotation(DataPermissionOnField.class) != null ){
                        if(interceptFields!="" && field.getAnnotation(DataPermissionOnField.class).value()!=""){ interceptFields+=",";}
                        interceptFields+=field.getAnnotation(DataPermissionOnField.class).value();
                    }
                }
            }
            value.setInterceptFields(interceptFields);
            ThreadLocalUtil.setDataPermissions(value);
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.removeDataPermissions();
        super.afterCompletion(request, response, handler, ex);
    }
}
