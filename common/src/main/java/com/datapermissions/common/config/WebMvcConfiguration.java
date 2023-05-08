package com.datapermissions.common.config;

import com.data.permissions.interceptor.DataPermissions2Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ConfigurationProperties("com.data.permissions")
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private DataPermissions2Interceptor dataPermissions2Interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dataPermissions2Interceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
