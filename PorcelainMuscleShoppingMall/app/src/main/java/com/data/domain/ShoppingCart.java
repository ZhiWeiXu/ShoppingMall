package com.data.domain;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/6/9.
 */
//购物车表
public class ShoppingCart extends BmobObject{
        private MyUser user;
        private Commodity commodity;

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
