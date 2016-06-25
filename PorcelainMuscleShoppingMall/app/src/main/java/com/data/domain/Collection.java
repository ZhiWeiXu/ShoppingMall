package com.data.domain;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/6/9.
 */
//用户的商品收藏表
public class Collection extends BmobObject{
        private MyUser user;//用户信息
        private Commodity commodity;//商品信息

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
