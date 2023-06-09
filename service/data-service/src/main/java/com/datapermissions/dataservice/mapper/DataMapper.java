package com.datapermissions.dataservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datapermissions.common.bean.DO.UserDataPermission;
import com.datapermissions.dataservice.bean.DO.DaySaleDO;
import com.datapermissions.dataservice.bean.DO.PromotionSelectionSingle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper extends BaseMapper<DaySaleDO> {

    //@DataPermission("acc_date,store_code")
    public List<DaySaleDO> getDaySale();

    public List<PromotionSelectionSingle> getPromotionSelectSingle();


}
