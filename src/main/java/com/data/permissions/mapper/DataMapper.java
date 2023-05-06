package com.data.permissions.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.permissions.annotation.DataPermission;
import com.data.permissions.bean.DO.DaySaleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper extends BaseMapper<DaySaleDO> {

    //@DataPermission("acc_date,store_code")
    public List<DaySaleDO> getDaySale();


}
