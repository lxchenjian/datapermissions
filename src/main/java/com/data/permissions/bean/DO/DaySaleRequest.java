package com.data.permissions.bean.DO;

import com.data.permissions.annotation.DataPermissionOnClass;
import com.data.permissions.annotation.DataPermissionOnField;

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
