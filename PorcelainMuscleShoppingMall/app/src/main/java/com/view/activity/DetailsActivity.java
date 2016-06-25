package com.view.activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.data.domain.ShoppingCart;
import com.data.domain.*;
import com.logic.service.MyDetailsAdapter;
import com.logic.utils.LoadImagesUtils;
import com.logic.utils.Tupianjiazai;

import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
public class DetailsActivity extends Activity implements View.OnClickListener{
    private int number = 0;
    private Handler handler;
    private Handler handler2;
    private List<String> urls = new ArrayList<String>();
    private MyDetailsAdapter myDetailsAdapter;
    private List<Comment> commentList = new ArrayList<Comment>();
    private TextView details_name,details_price,kuaidi,yunfei,details_collection;
    LinearLayout city_menu;
    private int j = 0;
    private Commodity commodity;
    private Button addvehicle,purchase;
    private String uuuuuu;
    private ImageView details_topimages,details_collection_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Intent intent = getIntent();
        commodity = (Commodity)intent.getSerializableExtra("commodity");
        uuuuuu = intent.getStringExtra("url");
        View list_head = LayoutInflater.from(this).inflate(R.layout.listview_handleview,null);
        details_name = (TextView)list_head.findViewById(R.id.details_name);
        details_price = (TextView)list_head.findViewById(R.id.details_price);
        kuaidi = (TextView)list_head.findViewById(R.id.kuaidi);
        yunfei = (TextView)list_head.findViewById(R.id.yunfei);
        details_collection = (TextView)list_head.findViewById(R.id.details_collection);
        details_collection_icon = (ImageView)list_head.findViewById(R.id.details_collection_icon);
        details_topimages = (ImageView)list_head.findViewById(R.id.details_topimages);
        details_name.setText(commodity.getCommodityname());
        details_price.setText(commodity.getPrice());
        kuaidi.setText(commodity.getExpresscompany());
        yunfei.setText(commodity.getFreight());
        myDetailsAdapter = new MyDetailsAdapter(this);
        city_menu=(LinearLayout)findViewById(R.id.buy_ctiy);
        details_name = (TextView)findViewById(R.id.details_name);
        details_price = (TextView)findViewById(R.id.details_price);
        kuaidi = (TextView)findViewById(R.id.kuaidi);
        yunfei = (TextView)findViewById(R.id.yunfei);
        addvehicle = (Button)findViewById(R.id.addvehicle);
        purchase = (Button)findViewById(R.id.purchase);
        addvehicle.setOnClickListener(this);
        purchase.setOnClickListener(this);
        details_collection.setOnClickListener(this);


        ListView listView = (ListView)findViewById(R.id.myccommentlist);
        listView.addHeaderView(list_head);
        listView.setAdapter(myDetailsAdapter);
        new LoadImagesUtils(details_topimages).LoadImaegs(commodity.getURL());
        Loadreview();
    }
    //加载评论
    public void Loadreview(){
        BmobQuery<Comment> query = new BmobQuery<Comment>();
        query.include("user");
        query.setLimit(10);
        query.setSkip(number);
        query.addWhereEqualTo("commodity",new BmobPointer(commodity));
        query.findObjects(DetailsActivity.this, new FindListener<Comment>() {
            @Override
            public void onSuccess(List<Comment> list) {
                if(list.size() != 0){
                     for(Comment comment : list){
                          MyUser user = comment.getUser();
                          Commentdata commentdata = new Commentdata();
                          commentdata.setUsername(user.getUsername());
                          commentdata.setUrl(user.getUserimage());
                          commentdata.setContent(comment.getContent());
                          myDetailsAdapter.addlist(commentdata);
                     }
                }

            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
    public void Buy(){
        if(MainActivity.userInfo!=null) {
            UserOrder userOrder = new UserOrder();
            userOrder.setUser(MainActivity.userInfo);
            userOrder.setCommodity(commodity);
            userOrder.setTransactionstate("买家已付款");
            userOrder.save(DetailsActivity.this, new SaveListener() {
                @Override
                public void onSuccess() {
                    Snackbar snackbar = Snackbar.make(purchase, "购买成功", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(Color.RED);
                    snackbar.show();
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        }else{
            Intent intent = new Intent(DetailsActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
    public void AddShoppingCart(){
                       if(MainActivity.userInfo!=null) {
                           ShoppingCart shoppingCart = new ShoppingCart();
                           shoppingCart.setUser(MainActivity.userInfo);
                           shoppingCart.setCommodity(commodity);
                           shoppingCart.save(DetailsActivity.this, new SaveListener() {
                               @Override
                               public void onSuccess() {
                                   Snackbar snackbar = Snackbar.make(addvehicle, "已将该商品添加入购物车", Snackbar.LENGTH_LONG);
                                   View view = snackbar.getView();
                                   ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(Color.RED);
                                   snackbar.show();
                               }

                               @Override
                               public void onFailure(int i, String s) {

                               }
                           });
                       }else {
                           Intent intent = new Intent(DetailsActivity.this,LoginActivity.class);
                           startActivity(intent);
                       }
    }

    public void addCollection(){
        if(MainActivity.userInfo!=null) {
            details_collection_icon.setBackgroundResource(R.drawable.collection_2);
            Collection collection = new Collection();
            collection.setUser(MainActivity.userInfo);
            collection.setCommodity(commodity);
            collection.save(DetailsActivity.this, new SaveListener() {
                @Override
                public void onSuccess() {
                    Snackbar snackbar = Snackbar.make(addvehicle, "收藏成功", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(Color.RED);
                    snackbar.show();
                }

                @Override
                public void onFailure(int i, String s) {
                }
            });
        }else {
            Intent intent = new Intent(DetailsActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addvehicle:
                AddShoppingCart();
                break;
            case R.id.purchase:
                Buy();
                break;
            case R.id.details_collection:
                addCollection();
                break;
            case R.id.buy_ctiy :
//                dialog1();
                Toast.makeText(getApplicationContext(),"sssss",Toast.LENGTH_LONG).show();
                break;

        }
    }
}
