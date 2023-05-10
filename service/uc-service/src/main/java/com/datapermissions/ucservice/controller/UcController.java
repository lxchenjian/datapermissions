package com.datapermissions.ucservice.controller;


import com.datapermissions.common.bean.DO.UserDO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/uc")
public class UcController {


    @PostMapping("/getUserPermission")
    public List<UserDO> getUserPermission(){

        return null;
    }

}
