package com.datapermissions.ucservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UcServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcServiceApplication.class, args);
    }

}
