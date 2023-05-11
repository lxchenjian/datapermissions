package com.datapermissions.dataservice.bean.DO;


import com.datapermissions.common.annotation.DataPermissionOnClass;
import com.datapermissions.common.annotation.DataPermissionOnField;

@DataPermissionOnClass("a.store_code")
public class DaySaleRequest {

    @DataPermissionOnField("a.acc_date")
    private String accDate;

    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }
}
