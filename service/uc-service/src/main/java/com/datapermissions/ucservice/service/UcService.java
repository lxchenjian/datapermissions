package com.datapermissions.ucservice.service;

import com.datapermissions.common.bean.DO.UserDataPermission;

import java.util.List;

public interface UcService {

    public List<UserDataPermission> getUserPermission(String userId);
}
