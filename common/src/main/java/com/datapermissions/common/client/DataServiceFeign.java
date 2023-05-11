package com.datapermissions.common.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "data-service",url = "http://192.168.1.170:8888")
public interface DataServiceFeign {

}
