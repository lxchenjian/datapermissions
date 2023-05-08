package com.data.permissions.service.impl;

import com.data.permissions.bean.DO.PromotionSelectionSingle;
import com.data.permissions.mapper.DataMapper;
import com.data.permissions.service.PromotionSelectionSingleService;
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
