package com.datapermissions.ucservice.controller;


import com.datapermissions.common.annotation.DisableDataPermission;
import com.datapermissions.common.bean.DO.UserDataPermission;
import com.datapermissions.ucservice.service.UcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/uc")
public class UcController {

    @Autowired
    private final UcService ucService;

    public UcController(UcService ucService) {
        this.ucService = ucService;
    }
    @DisableDataPermission
    @PostMapping("/getUserPermission")
    public List<UserDataPermission> getUserPermission(@RequestParam("userId") String userId){
        return ucService.getUserPermission(userId);
    }

}
