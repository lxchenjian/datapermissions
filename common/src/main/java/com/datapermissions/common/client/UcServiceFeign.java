package com.datapermissions.common.client;

import com.datapermissions.common.bean.DO.UserDataPermission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("uc-service")
public interface UcServiceFeign {

    @PostMapping("/uc/getUserPermission")
    public List<UserDataPermission> getUserPermission();
}
