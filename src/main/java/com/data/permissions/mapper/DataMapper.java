package com.data.permissions.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.data.permissions.annotation.DataPermission;
import com.data.permissions.bean.DO.DaySaleDO;
import com.data.permissions.bean.DO.PromotionSelectionSingle;
import com.data.permissions.bean.DO.UserDataPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper
public interface DataMapper extends BaseMapper<DaySaleDO> {

    //@DataPermission("acc_date,store_code")
    public List<DaySaleDO> getDaySale();


    public List<UserDataPermission> getDataPermission();


    public List<PromotionSelectionSingle> getPromotionSelectSingle();


}
