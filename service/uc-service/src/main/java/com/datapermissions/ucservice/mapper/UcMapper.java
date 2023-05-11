package com.datapermissions.ucservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.datapermissions.common.bean.DO.UserDataPermission;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UcMapper extends BaseMapper {


    public List<UserDataPermission> getUserPermission(@Param("userId") String userId);
}
