package com.datapermissions.common.bean.DO;

import javax.swing.plaf.TreeUI;
import java.util.Objects;

public class DataPermission {

    private String interceptFields = "";

    private boolean whetherToIntercept = true;

    public DataPermission() {

    }

    public String getInterceptFields() {
        return interceptFields;
    }

    public void setInterceptFields(String interceptFields) {
        this.interceptFields = interceptFields;
    }

    public boolean getWhetherToIntercept() {
        return whetherToIntercept;
    }

    public void setWhetherToIntercept(boolean whetherToIntercept) {
        this.whetherToIntercept = whetherToIntercept;
    }
}
