package com.datapermissions.common.client;

import com.datapermissions.common.bean.DO.UserDataPermission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "uc-service",url = "http://192.168.1.170:8889")
public interface UcServiceFeign {

    @PostMapping("/uc/getUserPermission")
    public List<UserDataPermission> getUserPermission(@RequestParam("userId") String userId);
}
