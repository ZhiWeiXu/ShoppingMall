package com.data.domain;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/6/9.
 */
public class UserOrder extends BmobObject{
        private MyUser user;//用户信息
        private Commodity commodity;//商品信息
        private String transactionstate;//交易状态

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

    public String getTransactionstate() {
        return transactionstate;
    }

    public void setTransactionstate(String transactionstate) {
        this.transactionstate = transactionstate;
    }
}
