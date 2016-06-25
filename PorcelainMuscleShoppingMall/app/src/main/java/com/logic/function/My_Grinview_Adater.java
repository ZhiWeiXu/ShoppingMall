package com.logic.function;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.data.domain.Commodity;
import com.data.domain.Listdata;
import com.logic.utils.Tupianjiazai;
import com.view.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
public class My_Grinview_Adater extends BaseAdapter{
    private Context context;
    private static List<Listdata> list;
    Commodity commodity=new Commodity();
    public My_Grinview_Adater(Context context) {
        this.context = context;
        list = new ArrayList<Listdata>();
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public void addlist(Listdata listdata){

        list.add(listdata);
        notifyDataSetChanged();

    }
    public List<Listdata> getlist(){
        return list;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.grinview_info_menu,null);
        }
        ImageView gridview_inf0_image = (ImageView)convertView.findViewById(R.id.gridview_inf0_image);
        TextView gridview_inf0_table = (TextView)convertView.findViewById(R.id.gridview_inf0_table);
        TextView gridview_inf0_conten = (TextView)convertView.findViewById(R.id.gridview_inf0_conten);
        TextView gridview_inf0_money = (TextView)convertView.findViewById(R.id.gridview_inf0_money);
        TextView gridview_inf0_suliang = (TextView)convertView.findViewById(R.id.gridview_inf0_suliang);
        TextView gridview_inf0_city = (TextView)convertView.findViewById(R.id.gridview_inf0_city);
        Listdata listdata = getItem(position);
        new Tupianjiazai().getPicture(listdata.getImagrurl(), gridview_inf0_image);
        Log.d("______", "" + listdata.getImagrurl());
                gridview_inf0_table.setText(listdata.getTitle());
        gridview_inf0_conten.setText(listdata.getDescribe());
        gridview_inf0_money.setText(listdata.getPrice());
        gridview_inf0_suliang.setText(listdata.getShuliang());
        gridview_inf0_city.setText(listdata.getCity());

        return convertView;
    }
}
