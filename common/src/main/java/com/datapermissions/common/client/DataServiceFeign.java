package com.datapermissions.common.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("data-service")
public class DataServiceFeign {

}
