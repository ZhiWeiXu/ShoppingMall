package com.data.domain;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/6/5.
 */
public class MyUser extends BmobUser implements Serializable{
    private String userimage;//用户头像
    private boolean member;//是否为会员
    private Useraddress useraddress;//用户地址

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public Useraddress getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(Useraddress useraddress) {
        this.useraddress = useraddress;
    }
}
