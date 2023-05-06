package com.data.permissions.interceptor;

import com.data.permissions.annotation.DataPermissionOnClass;
import com.data.permissions.annotation.DataPermissionOnField;
import com.data.permissions.util.ThreadLocalUtil;
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
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Class[] methodParams = handlerMethod.getMethod().getParameterTypes();
        if(methodParams.length <= 0){
            return super.preHandle(request, response, handler);
        }
        String value ="";
        for(Class methodParam:methodParams){
            if(methodParam.getAnnotation(DataPermissionOnClass.class) != null){
                String temp = ((DataPermissionOnClass)methodParam.getAnnotation(DataPermissionOnClass.class)).value();
                if(temp != ""){
                    if(value!=""){ value+=",";}
                    value+=temp;
                }
            }

            Field[] fields = methodParam.getDeclaredFields();
            for(Field field:fields){
                if( field.getAnnotation(DataPermissionOnField.class) != null ){
                    if(value!="" && field.getAnnotation(DataPermissionOnField.class).value()!=""){ value+=",";}
                    value+=field.getAnnotation(DataPermissionOnField.class).value();
                }
            }
        }
        ThreadLocalUtil.setDataPermissions(value);
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
