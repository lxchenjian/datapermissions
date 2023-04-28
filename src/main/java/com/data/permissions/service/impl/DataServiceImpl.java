package com.data.permissions.service.impl;

import com.data.permissions.bean.DO.DaySaleDO;
import com.data.permissions.mapper.DataMapper;
import com.data.permissions.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;
    @Override
    public List<DaySaleDO> getDaySale() {
        return dataMapper.getDaySale();
    }
}
