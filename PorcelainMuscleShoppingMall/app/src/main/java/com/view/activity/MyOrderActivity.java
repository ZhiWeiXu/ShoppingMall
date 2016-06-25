package com.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import com.data.domain.MyUser;
import com.data.domain.Orderdata;
import com.data.domain.UserOrder;
import com.logic.service.MyOrderAdapter;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
public class MyOrderActivity extends Activity{
    private ListView listView;
    private MyOrderAdapter myOrderAdapter;
    private MyUser user;
    private Handler handler;
    private Handler handler2;
    private List<UserOrder> userOrderList;
    private List<String> urls;
    private int number = 0;
    private int j = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);
        Intent intent = getIntent();
        user = (MyUser)intent.getSerializableExtra("user");
        userOrderList = new ArrayList<UserOrder>();
        urls = new ArrayList<String>();
        myOrderAdapter = new MyOrderAdapter(this);
        listView = (ListView)findViewById(R.id.myorder_list);
        listView.setAdapter(myOrderAdapter);
        selectOrder();
    }
    public void selectOrder(){
        BmobQuery<UserOrder> query = new BmobQuery<UserOrder>();
        query.setLimit(10);
        query.setSkip(number);
        query.addWhereEqualTo("user",new BmobPointer(user));
        query.include("commodity,commodity.commodityUrl");
        query.findObjects(MyOrderActivity.this, new FindListener<UserOrder>() {
            @Override
            public void onSuccess(List<UserOrder> list) {
                if(list.size()!=0){
                    for(UserOrder userOrder : list){
                        Orderdata orderdata = new Orderdata();
                        orderdata.setPrice(userOrder.getCommodity().getPrice());
                        orderdata.setName(userOrder.getCommodity().getCommodityname());
                        orderdata.setState(userOrder.getTransactionstate());
                        orderdata.setDescribe(userOrder.getCommodity().getDescribe());
                        orderdata.setUrl(userOrder.getCommodity().getCommodityUrl().getUrl1());
                        myOrderAdapter.addlist(orderdata);
                    }
                    number = number + 10;
                    selectOrder();
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
