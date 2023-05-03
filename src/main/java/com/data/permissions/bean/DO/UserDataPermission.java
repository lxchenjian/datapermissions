package com.data.permissions.bean.DO;

import java.util.List;

public class UserDataPermission {

    private String fieldName;

    private List<String> values;

    public UserDataPermission(){

    }

    public UserDataPermission(String fieldName ,List<String> values){
        this.fieldName = fieldName;
        this.values = values;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
