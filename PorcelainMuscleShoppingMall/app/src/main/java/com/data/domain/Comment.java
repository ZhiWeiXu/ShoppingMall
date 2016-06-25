package com.data.domain;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/6/8.
 */
public class Comment extends BmobObject{
    private String content;
    private MyUser user;
    private Commodity commodity;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}
