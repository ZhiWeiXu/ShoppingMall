package com.data.domain;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/7.
 */
public class Listdata implements  Serializable{
    private String ID;


    private String images;//图片
    private String title;//标题
    private String price;//价格
    private String describe;//内容
    private String name;//商家
    private String number;//数量
    private String classification;//类别
    private String freight;//邮费
    private  String expresscompany;//快递类别
    private String city;//商品所在地
    private String shuliang;//商品访问量
    Commodity commodity;
    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }




    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getExpresscompany() {
        return expresscompany;
    }

    public void setExpresscompany(String expresscompany) {
        this.expresscompany = expresscompany;
    }


    private  String imagrurl;

    public String getImagrurl() {
        return imagrurl;
    }

    public void setImagrurl(String imagrurl) {
        this.imagrurl = imagrurl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Commodity getCommdity(){
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}
