package com.data.domain;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/6/7.
 */
public class urse extends BmobObject{
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
