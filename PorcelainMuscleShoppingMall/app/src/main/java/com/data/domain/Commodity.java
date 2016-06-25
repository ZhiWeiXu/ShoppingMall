package com.data.domain;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/6/7.
 */
//商品的基本信息表
public class Commodity extends BmobObject implements Serializable{
        private String commodityname;//商品名
        private String price;//商品价格
        private String describe;//商品描述
        private CommodityUrl commodityUrl;//商品的URL地址
        private String expresscompany;//快递公司
        private String freight;//运费
        private String placeofdelivery;//发货地点
        private String classification;//商品类别
        private String URL;

     public String getURL() {
        return URL;
     }

     public void setURL(String URL) {
        this.URL = URL;
     }

    public String getExpresscompany() {
        return expresscompany;
    }

    public void setExpresscompany(String expresscompany) {
        this.expresscompany = expresscompany;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getPlaceofdelivery() {
        return placeofdelivery;
    }

    public void setPlaceofdelivery(String placeofdelivery) {
        this.placeofdelivery = placeofdelivery;
    }


    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CommodityUrl getCommodityUrl() {
        return commodityUrl;
    }

    public void setCommodityUrl(CommodityUrl commodityUrl) {
        this.commodityUrl = commodityUrl;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
