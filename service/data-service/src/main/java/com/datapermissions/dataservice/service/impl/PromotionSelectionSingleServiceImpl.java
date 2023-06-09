package com.datapermissions.dataservice.service.impl;

import com.datapermissions.dataservice.bean.DO.PromotionSelectionSingle;
import com.datapermissions.dataservice.mapper.DataMapper;
import com.datapermissions.dataservice.service.PromotionSelectionSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionSelectionSingleServiceImpl implements PromotionSelectionSingleService {

    @Autowired
    private DataMapper dataMapper;


    public List<PromotionSelectionSingle> getPromotionSelectionSingle(){
        return dataMapper.getPromotionSelectSingle();
    }
}
