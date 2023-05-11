package com.datapermissions.dataservice.controller;

import com.datapermissions.dataservice.bean.DO.DaySaleDO;
import com.datapermissions.dataservice.bean.DO.DaySaleRequest;
import com.datapermissions.dataservice.bean.DO.PromotionRequest;
import com.datapermissions.dataservice.service.DataService;
import com.datapermissions.dataservice.service.PromotionSelectionSingleService;
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

    @Autowired
    private PromotionSelectionSingleService promotionSelectionSingleService;

    @GetMapping("/getSaleDate")
    public int getSaleDate(){
        List<DaySaleDO> list  = dataService.getDaySale();
        return 1;
    }

    @GetMapping("/getSaleDate_permission")
    public int getSaleDate_datapermission(DaySaleRequest daySaleRequest){
        List<DaySaleDO> list  = dataService.getDaySale();
        return 1;
    }

    @GetMapping("/getPromotionSelectionSingle")
    public int getPromotionSelectionSingle(){
        promotionSelectionSingleService.getPromotionSelectionSingle();
        return 1;
    }

    @GetMapping("/getPromotionSelectionSingle_permission")
    public int getPromotionSelectionSingle_permission(PromotionRequest promotionRequest){
        promotionSelectionSingleService.getPromotionSelectionSingle();
        return 1;
    }
}
