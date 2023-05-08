package com.data.permissions;

import com.data.permissions.interceptor.DataPermissionsInterceptor;
import com.data.permissions.mapper.DataMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DatapermissionsApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(DatapermissionsApplication.class, args);
//	}


	public static void main(String[] args) {
		// 1、返回我们ioc容器
		ConfigurableApplicationContext run = SpringApplication.run(DatapermissionsApplication.class, args);

		// 2、查看容器里面的组件
		DataMapper bean1 = run.getBean(DataMapper.class);
		DataPermissionsInterceptor bean = run.getBean(DataPermissionsInterceptor.class);

		System.out.println();
	}
}
