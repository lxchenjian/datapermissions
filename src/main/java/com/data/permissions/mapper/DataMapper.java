package com.data.permissions.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.permissions.bean.DO.DaySaleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper extends BaseMapper<DaySaleDO> {

    public List<DaySaleDO> getDaySale();


}
