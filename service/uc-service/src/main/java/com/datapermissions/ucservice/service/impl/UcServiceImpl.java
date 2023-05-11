package com.datapermissions.ucservice.service.impl;

import com.datapermissions.common.bean.DO.UserDataPermission;
import com.datapermissions.ucservice.mapper.UcMapper;
import com.datapermissions.ucservice.service.UcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UcServiceImpl implements UcService {

    @Autowired
    private UcMapper ucMapper;
    @Override
    public List<UserDataPermission> getUserPermission(String userId) {
        return ucMapper.getUserPermission(userId);
    }
}
