package com.data.domain;

import cn.bmob.v3.BmobObject;

/**
 * 商品销售状态表
 */
public class CommoditySell extends BmobObject{
    private Integer number;//销售数量
    private Commodity commodity;//销售的商品
    private MyUser user;//购买人
    private String monthlysales;//月销量
    public String getMonthlysales() {
        return monthlysales;
    }

    public void setMonthlysales(String monthlysales) {
        this.monthlysales = monthlysales;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }
}
