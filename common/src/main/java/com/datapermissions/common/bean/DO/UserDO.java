package com.datapermissions.common.bean.DO;


import java.util.ArrayList;
import java.util.List;

public class UserDO {

    private Long id;

    private String  name;

    private List<UserDataPermission> dataPermission;

    public UserDO() {
    }

    public UserDO(Long id, String name, List<UserDataPermission> dataPermission) {
        this.id = id;
        this.name = name;
        this.dataPermission = dataPermission;
    }

    public UserDO(Long id, String name) {
        this.id = id;
        this.name = name;
        this.dataPermission = new ArrayList<>();
        this.dataPermission.add(new UserDataPermission("acc_date","2023-01-01"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDataPermission> getDataPermission() {
        return dataPermission;
    }

    public void setDataPermission(List<UserDataPermission> dataPermission) {
        this.dataPermission = dataPermission;
    }
}
