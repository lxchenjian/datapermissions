package com.data.permissions.service;

import com.data.permissions.annotation.DataPermission;
import com.data.permissions.bean.DO.DaySaleDO;

import java.util.List;

public interface DataService {


    List<DaySaleDO> getDaySale();
}
