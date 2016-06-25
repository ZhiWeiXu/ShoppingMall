package com.data.domain;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/6/10.
 */
public class Homepage extends BmobObject{
        private Commodity commodity;
        private String URL1;

    public String getURL2() {
        return URL2;
    }

    public void setURL2(String URL2) {
        this.URL2 = URL2;
    }

    public String getURL1() {
        return URL1;
    }

    public void setURL1(String URL1) {
        this.URL1 = URL1;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    private String URL2;
}
