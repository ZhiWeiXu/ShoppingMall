package com.view.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.data.domain.Comment;
import com.data.domain.Commodity;
import com.data.domain.MyUser;
import com.data.domain.CommentAdapterData;
import com.logic.service.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

public class MyCommentActivity extends Activity{
    private CommentAdapter commentAdapter;
    private MyUser user;
    private List<Commodity> commodityList = new ArrayList<Commodity>();
    private int number = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commentactivity);
        Intent intent = getIntent();
         user = (MyUser)intent.getSerializableExtra("user");
        commentAdapter = new CommentAdapter(this);
        ListView listView = (ListView)findViewById(R.id.order_list);
        listView.setAdapter(commentAdapter);
        selectComment();

    }
    public void selectComment(){
        BmobQuery<Comment> query = new BmobQuery<Comment>();
        query.include("commodity,commodity.commodityUrl");
        query.setLimit(10);
        query.setSkip(number);
        query.addWhereEqualTo("user",new BmobPointer(user));
        query.findObjects(MyCommentActivity.this, new FindListener<Comment>() {
            @Override
            public void onSuccess(List<Comment> list) {
                 if(list.size()!=0){
                    for(Comment comment : list){
                        Commodity commodity = comment.getCommodity();
                      //  commodityList.add(commodity);
                        CommentAdapterData commentAdapterData = new CommentAdapterData();
                        commentAdapterData.setUrl(commodity.getCommodityUrl().getUrl1());
                        commentAdapterData.setDescribe(commodity.getDescribe());
                        commentAdapterData.setPrice(commodity.getPrice());
                        commentAdapterData.setName(commodity.getCommodityname());
                        commentAdapterData.setContent(comment.getContent());
                        commentAdapter.addlist(commentAdapterData);
                    }
                     number = number+10;
                     selectComment();
                 }
            }

            @Override
            public void onError(int i, String s) {
                Log.i("MainActivity",String.valueOf(i));
            }
        });
    }
}
