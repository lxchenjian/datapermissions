package com.data.permissions.controller;


import com.data.permissions.bean.DO.DaySaleDO;
import com.data.permissions.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/getSaleDate")
    public int getSaleDate(){
        List<DaySaleDO> list  = dataService.getDaySale();
        return 1;
    }
}
