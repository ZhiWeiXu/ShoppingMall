package com.logic.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.data.domain.Commentdata;
import com.data.domain.Listdata;
import com.logic.utils.LoadImagesUtils;
import com.view.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
public class MyLiviewAdapter extends BaseAdapter{
    private Context context;
    private static List<Listdata> list;
    private TextView title;
    private ImageView image;
    private TextView describe;
    private TextView price;
    private TextView name;
    private  TextView number;
    public MyLiviewAdapter(Context context) {
            this.context = context;
             list = new ArrayList<Listdata>();
    }
     public void addlist(Listdata listdata){

         list.add(listdata);
         notifyDataSetChanged();
     }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Listdata getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.buy,null);
            title = (TextView)convertView.findViewById(R.id.buy1);
            image = (ImageView)convertView.findViewById(R.id.image);
            describe = (TextView)convertView.findViewById(R.id.buy_2);
            price = (TextView)convertView.findViewById(R.id.buy_3);
            name = (TextView)convertView.findViewById(R.id.buy_1);
            number = (TextView)convertView.findViewById(R.id.buy_4);
            Listdata listdata = getItem(position);
            title.setText(listdata.getTitle());
            new LoadImagesUtils(image).LoadImaegs(listdata.getImages());
            describe.setText(listdata.getDescribe());
            name.setText(listdata.getName());
            number.setText(listdata.getNumber());
        }
        return convertView;
    }
}
