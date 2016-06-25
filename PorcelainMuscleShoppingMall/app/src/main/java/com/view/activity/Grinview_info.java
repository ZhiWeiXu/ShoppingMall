package com.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.data.domain.Commodity;
import com.data.domain.Listdata;
import com.logic.function.My_Grinview_Adater;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/6/8.
 */
public class Grinview_info extends Activity implements AdapterView.OnItemClickListener{
    private My_Grinview_Adater myGrinviewAdater;
    ImageView  grid_image;
    TextView grid_title;
    ListView gridView;
    private List<Commodity> commentList=new ArrayList<Commodity>();
    BmobQuery<Commodity> query=new BmobQuery<Commodity>();
    Bitmap bmp=null;
  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_info);
        grid_image=(ImageView)findViewById(R.id.grid_image);
        grid_title=(TextView)findViewById(R.id.grid_table);
        Intent intent=getIntent();
        String intent_I=intent.getStringExtra("id");
        Resources res1=getResources();
        myGrinviewAdater=new My_Grinview_Adater(getApplicationContext());

        gridView=(ListView)findViewById(R.id.grid_list);
        switch (intent_I){
            case ""+0:
                bmp=BitmapFactory.decodeResource(res1, R.drawable.cleansing);
                grid_image.setImageBitmap(bmp);
                grid_title.setText("洁面");
                GetData("洁面");
                break;
            case ""+1:
                bmp=BitmapFactory.decodeResource(res1, R.drawable.cream);
                grid_image.setImageBitmap(bmp);
                grid_title.setText("面霜");
                GetData("面霜");
                break;
            case ""+2:
                bmp=BitmapFactory.decodeResource(res1, R.drawable.essence);
                grid_image.setImageBitmap(bmp);
                grid_title.setText("精华");
                GetData("精华");
                break;
            case ""+3:
                bmp=BitmapFactory.decodeResource(res1, R.drawable.emulsion);
                grid_image.setImageBitmap(bmp);
                grid_title.setText("乳液");
                GetData("乳液");
                break;
            case ""+4:
                bmp=BitmapFactory.decodeResource(res1, R.drawable.facialmask);
                grid_image.setImageBitmap(bmp);
                grid_title.setText("面膜");
                GetData("面膜");
                break;
            case ""+5:
                bmp=BitmapFactory.decodeResource(res1, R.drawable.lipstick);
                grid_image.setImageBitmap(bmp);
                grid_title.setText("唇膏");
                GetData("唇膏");

                break;
        }
      grid_title.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent1=new Intent(getApplicationContext(),DetailsActivity.class);
              startActivity(intent1);
          }
      });
        gridView.setAdapter(myGrinviewAdater);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Listdata data=myGrinviewAdater.getlist().get(position);
          Commodity commodity=commentList.get(position);
      //  Toast.makeText(getApplicationContext(),commodity.getCommodityUrl().getUrl1()+commodity.getTableName(),Toast.LENGTH_SHORT).show();
         Intent intent = new Intent(Grinview_info.this,DetailsActivity.class);
          Bundle bundle=new Bundle();
          bundle.putSerializable("commodity", commodity);
          intent.putExtras(bundle);
           startActivity(intent);

    }

    private void GetData(String type){

        query.addWhereEqualTo("classification", type);
        query.include("commodityUrl");
        query.findObjects(this, new FindListener<Commodity>() {

            @Override
            public void onSuccess(List<Commodity> list) {

                for (com.data.domain.Commodity commodity : list) {

                    Listdata listdata = new Listdata();

                    listdata.setID(commodity.getObjectId());
                    listdata.setImagrurl(commodity.getURL());
                    listdata.setTitle(commodity.getCommodityname());
                    listdata.setDescribe(commodity.getDescribe());
                    listdata.setPrice(commodity.getPrice());
                    listdata.setShuliang(commodity.getExpresscompany());
                    listdata.setCity(commodity.getPlaceofdelivery());
                    listdata.setFreight(commodity.getFreight());
                    listdata.setClassification(commodity.getClassification());

                    listdata.setCommodity(commodity);
                    commentList.add(commodity);
                    myGrinviewAdater.addlist(listdata);


                }
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(getApplicationContext(),"数据加载失败，请检查网络",Toast.LENGTH_LONG).show();
            }
        });

    }
}
