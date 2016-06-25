package com.logic.service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.data.domain.CommentAdapterData;
import com.logic.utils.LoadImagesUtils;
import com.logic.utils.Tupianjiazai;
import com.view.activity.R;
import java.util.ArrayList;
import java.util.List;
public class CommentAdapter extends BaseAdapter{
    private List<CommentAdapterData> list;
    private Context context;
    private ImageView comment_icon;
    private TextView comment_tv1;
    private  TextView comment_tv2;
    private TextView comment_tv3;
    private TextView comment_content;

    public CommentAdapter(Context context) {
        this.context = context;
        list = new ArrayList<CommentAdapterData>();
    }
    public void addlist(CommentAdapterData orderAdapterData){
        list.add(orderAdapterData);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CommentAdapterData getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_list_ltem,null);
            comment_icon = (ImageView)convertView.findViewById(R.id.comment_icon);
            comment_tv1 = (TextView)convertView.findViewById(R.id.comment_tv1);
            comment_tv2 = (TextView)convertView.findViewById(R.id.comment_tv2);
            comment_tv3 = (TextView)convertView.findViewById(R.id.comment_tv3);
            comment_content = (TextView)convertView.findViewById(R.id.comment_content);
            CommentAdapterData orderAdapterData = getItem(position);
            new LoadImagesUtils(comment_icon).LoadImaegs(orderAdapterData.getUrl());
            comment_tv1.setText(orderAdapterData.getName());
            comment_tv2.setText(orderAdapterData.getDescribe());
            comment_tv3.setText(orderAdapterData.getPrice());
            comment_content.setText(orderAdapterData.getContent());
        }
        return convertView;
    }
}
