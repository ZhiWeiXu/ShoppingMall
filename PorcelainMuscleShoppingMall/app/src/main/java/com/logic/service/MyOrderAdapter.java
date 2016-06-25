package com.logic.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.data.domain.Orderdata;
import com.logic.utils.LoadImagesUtils;
import com.logic.utils.Tupianjiazai;
import com.view.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
   public class MyOrderAdapter extends BaseAdapter{
   private List<Orderdata> list;
    private ImageView myorder_icon;
    private TextView myorder_tv1;
    private TextView myorder_tv2;
    private TextView myorder_tv3;
    private TextView myorder_tv4;

    private Context context;

    public MyOrderAdapter(Context context) {
        this.context = context;
        list = new ArrayList<Orderdata>();
    }
    public void addlist(Orderdata orderdata){
        list.add(orderdata);
        notifyDataSetChanged();
    }
    public int getCount() {
        return list.size();
    }

    @Override
    public Orderdata getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.order_list_item,null);
            myorder_icon = (ImageView)convertView.findViewById(R.id.myorder_icon);
            myorder_tv1 = (TextView)convertView.findViewById(R.id.myorder_tv1);
            myorder_tv2 = (TextView)convertView.findViewById(R.id.myorder_tv2);
            myorder_tv3 = (TextView)convertView.findViewById(R.id.myorder_tv3);
            myorder_tv4 = (TextView)convertView.findViewById(R.id.myorder_tv4);
            Orderdata orderdata = getItem(position);
            new LoadImagesUtils(myorder_icon).LoadImaegs(orderdata.getUrl());
            myorder_tv1.setText(orderdata.getName());
            myorder_tv2.setText(orderdata.getDescribe());
            myorder_tv3.setText(orderdata.getPrice());
            myorder_tv4.setText(orderdata.getState());
        }
        return convertView;
    }
}
