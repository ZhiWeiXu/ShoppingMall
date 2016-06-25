package com.data.domain;

import cn.bmob.v3.BmobObject;

/**
 * 商品的图片链接表
 */
public class CommodityUrl extends BmobObject {
    private String url1;
    private String url2;

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }
}
