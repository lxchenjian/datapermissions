package com.data.permissions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.data.permissions.bean.DO.DaySaleDO;
import com.data.permissions.bean.DO.UserDO;
import com.data.permissions.mapper.DataMapper;
import com.data.permissions.service.DataService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataMapper dataMapper;
    @Override
    public List<DaySaleDO> getDaySale() {
        //List<DaySaleDO> users = dataMapper.getDaySale();
        List<DaySaleDO> users = dataMapper.selectList(new LambdaQueryWrapper<>());
        return users;
    }
}
