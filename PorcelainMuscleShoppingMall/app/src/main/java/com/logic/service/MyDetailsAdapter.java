package com.logic.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.data.domain.Commentdata;
import com.logic.utils.LoadImagesUtils;
import com.logic.utils.Tupianjiazai;
import com.view.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class MyDetailsAdapter extends BaseAdapter{
    private Context context;
    private List<Commentdata> list;
    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;
    public MyDetailsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<Commentdata>();
    }
    public void addlist(Commentdata commentdata){
            list.add(commentdata);
            notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Commentdata getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.commentlist_item,null);
            imageView = (ImageView)convertView.findViewById(R.id.comment_icon);
            textView1 = (TextView)convertView.findViewById(R.id.comment_tv1);
            textView2 = (TextView)convertView.findViewById(R.id.comment_tv2);
            Commentdata commentdata = getItem(position);
            new LoadImagesUtils(imageView).LoadImaegs(commentdata.getUrl());
            textView1.setText(commentdata.getUsername());
            textView2.setText(commentdata.getContent());
        }

        return convertView;
    }
}
