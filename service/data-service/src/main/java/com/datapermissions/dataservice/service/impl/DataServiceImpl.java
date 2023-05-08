package com.datapermissions.dataservice.service.impl;


import com.datapermissions.dataservice.bean.DO.DaySaleDO;
import com.datapermissions.dataservice.mapper.DataMapper;
import com.datapermissions.dataservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;
    @Override
    public List<DaySaleDO> getDaySale() {
        List<DaySaleDO> users = dataMapper.getDaySale();
        //List<DaySaleDO> users = dataMapper.selectList(new LambdaQueryWrapper<>());
        return users;
    }
}
