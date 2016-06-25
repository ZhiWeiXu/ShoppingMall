package com.logic.service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.data.domain.Collectiondata;
import com.logic.utils.LoadImagesUtils;
import com.logic.utils.Tupianjiazai;
import com.view.activity.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
public class CollectionAdapter extends BaseAdapter{
    private List<Collectiondata> list;
    private Context context;
    private ImageView collection_icon;
    private TextView collection_tv1;
    private TextView collection_tv2;
    private TextView collection_tv3;
    public CollectionAdapter(Context context) {
        this.context = context;
        list = new ArrayList<Collectiondata>();
    }
    public void addlist(Collectiondata collectiondata){
            list.add(collectiondata);
            notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Collectiondata getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.collection_list_item,null);
            collection_icon = (ImageView)convertView.findViewById(R.id.collection_icon);
            collection_tv1 = (TextView)convertView.findViewById(R.id.collection_tv1);
            collection_tv2 = (TextView)convertView.findViewById(R.id.collection_tv2);
            collection_tv3 = (TextView)convertView.findViewById(R.id.collection_tv3);
            Collectiondata collectiondata = getItem(position);
            new LoadImagesUtils(collection_icon).LoadImaegs(collectiondata.getUrl());
            collection_tv1.setText(collectiondata.getName());
            collection_tv2.setText(collectiondata.getDescribe());
            collection_tv3.setText(collectiondata.getPrice());
        }
        return convertView;
    }
}
