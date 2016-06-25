package com.view.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.data.domain.Collection;
import com.data.domain.Collectiondata;
import com.data.domain.Commodity;
import com.data.domain.MyUser;
import com.logic.service.CollectionAdapter;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
public class MyCollectionActivity extends Activity{
    private ListView collection_list;
    private MyUser user;
    private CollectionAdapter collectionAdapter;
    private int number = 0;
    //保存商品信息的集合
    private List<Commodity> CommodityInfo = new ArrayList<Commodity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_layout);
        Intent intent = getIntent();
        user = (MyUser)intent.getSerializableExtra("user");
        collectionAdapter = new CollectionAdapter(this);
        collection_list = (ListView)findViewById(R.id.collection_list);
        collection_list.setAdapter(collectionAdapter);
        selectCollection();
        collection_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转页面并且传递商品信息对象
                    Commodity commodity = CommodityInfo.get(position);
                    Intent intent = new Intent(MyCollectionActivity.this,DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("commodity",commodity);
                    intent.putExtras(bundle);
                    startActivity(intent);
            }
        });
    }
    public void selectCollection(){
        BmobQuery<Collection> query = new BmobQuery<Collection>();
        query.include("commodity,commodity.commodityUrl");
        query.setLimit(10);
        query.setSkip(number);;
        query.addWhereEqualTo("user",new BmobPointer(MainActivity.userInfo));
        query.findObjects(MyCollectionActivity.this, new FindListener<Collection>() {
            @Override
            public void onSuccess(List<Collection> list) {
                if(list.size()!=0) {
                   for(Collection collection : list){
                       Commodity commodity = collection.getCommodity();
                       CommodityInfo.add(commodity);
                       Collectiondata collectiondata = new Collectiondata();
                       collectiondata.setPrice(commodity.getPrice());
                       collectiondata.setName(commodity.getCommodityname());
                       collectiondata.setDescribe(commodity.getDescribe());
                       collectiondata.setUrl(commodity.getURL());
                       collectionAdapter.addlist(collectiondata);
                   }
                    number = number+10;
                    selectCollection();
                }
            }
            @Override
            public void onError(int i, String s) {
            }
        });
    }

}
