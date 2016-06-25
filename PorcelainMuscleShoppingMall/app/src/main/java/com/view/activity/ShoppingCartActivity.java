package com.view.activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RadialGradient;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.data.domain.Commodity;
import com.data.domain.Listdata;
import com.data.domain.ShoppingCart;
import com.logic.service.MyLiviewAdapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/5/31.
 */
public class ShoppingCartActivity extends Fragment implements View.OnClickListener{
    TextView buy_bianji,buy_xiaoxi,buy_jiesuan,buy_jiner;
    RadialGradient buy＿radioButton;
    ListView listview=null;
    List<Map<String, Object>> data;
  /*  String[]  buy_tible = new String[]{"正品行货，品质保证","正品行货，品质保证","正品行货，品质保证","正品行货，品质保证","正品行货，品质保证"};
    int[] buy_image = {R.drawable.tu1, R.drawable.tu2,R.drawable.tu3, R.drawable.tu3,R.drawable.tu3};
    String[] buy_tible1 = {"韩国补水", "BB霜","防赛霜","护肤保健","面膜"};//商品名称
    String[] buy_tible2 = {"高夫赤道防晒酷爽凝乳SPF30+ 75ml夏日防护 不油腻 ", "清爽平衡护肤基础套装","AAAAAAAAAAAAAAAAAAAAAAAAAA", "BBBBBBBBBBBBBBB","ssssssssssssssssssss"};//商品描述
    String[] buy_tible3 = {"$30.0", "$150.0","$150.0","$150.0","$150.0"};//商品价格
    String[] buy_tible4 = {"X30", "X1","X30","X10","X30",};//加入购物车的数量*/
    private Handler handler;
    private Handler handler2;
    private MyLiviewAdapter myAdapter;
    private List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();
    private List<String> urls = new ArrayList<String>() ;
    private List<Commodity> list = new ArrayList<Commodity>();//用于保存商品的信息
    private int number = 0;
    private int j = 0;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2,null);
        listview=(ListView)view.findViewById(R.id.buy_list);
        buy_jiesuan=(TextView)view.findViewById(R.id.buy_jiesuan);
        buy_bianji=(TextView)view.findViewById(R.id.buy_bianji);
        buy_xiaoxi=(TextView)view.findViewById(R.id.buy_xiaoxi);
        buy_jiner=(TextView)view.findViewById(R.id.buy_jiner);
        buy_jiesuan.setOnClickListener(this);
        buy_bianji.setOnClickListener(this);
        buy_xiaoxi.setOnClickListener(this);
        buy_jiner.setOnClickListener(this);
        data = new ArrayList<Map<String, Object>>();
        myAdapter = new MyLiviewAdapter(getContext());
        listview.setAdapter(myAdapter);
        selectShoppingCart();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //点击时跳转至商品详情页面
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(),DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("commodity",list.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_jiesuan:
                break;
            case R.id.buy_bianji:
                break;
            case R.id.buy_xiaoxi:
                break;
            case R.id.buy_jiner:
                break;
        }
    }
    //查询数据
     public void selectShoppingCart(){
         BmobQuery<ShoppingCart> query = new BmobQuery<ShoppingCart>();
         query.setLimit(10);
         query.setSkip(number);
         query.addWhereEqualTo("user",MainActivity.userInfo);
         query.include("commodity,commodity.commodityUrl");
         query.findObjects(getActivity(), new FindListener<ShoppingCart>() {
             @Override
             public void onSuccess(List<ShoppingCart> list) {
                     if(list.size()!=0){
                         for(ShoppingCart shoppingCart : list){
                             Listdata listdata = new Listdata();
                             Commodity commodity = shoppingCart.getCommodity();
                             listdata.setImages(commodity.getURL());
                             listdata.setDescribe(commodity.getDescribe());
                             listdata.setPrice(commodity.getPrice());
                             listdata.setName(commodity.getCommodityname());
                             listdata.setTitle("正品行货,品质保证");
                             myAdapter.addlist(listdata);
                         }
                     }
             }

             @Override
             public void onError(int i, String s) {

             }
         });
     }
}
