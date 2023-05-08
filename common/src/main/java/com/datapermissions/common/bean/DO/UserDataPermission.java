package com.datapermissions.common.bean.DO;

import java.util.List;

public class UserDataPermission {

    private String fieldName;

    private int permissionType;

    private String values;

    public UserDataPermission(){

    }

    public UserDataPermission(String fieldName, String values) {
        this.fieldName = fieldName;
        this.values = values;
    }

    public UserDataPermission(String fieldName, int permissionType, String values) {
        this.fieldName = fieldName;
        this.permissionType = permissionType;
        this.values = values;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(int permissionType) {
        this.permissionType = permissionType;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
