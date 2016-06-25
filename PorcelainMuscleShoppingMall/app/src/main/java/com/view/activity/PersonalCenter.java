package com.view.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.data.domain.MyUser;

import cn.bmob.v3.BmobUser;

public class PersonalCenter extends Fragment implements View.OnClickListener{
    private ImageButton tv_nave1;//我的评价
    private ImageButton tv_nave2;//我的订单
    private ImageButton tv_nave3;//我的收藏
    TextView butzhuxiao;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3,null);
        ImageView userimage = (ImageView)view.findViewById(R.id.userimage);
        TextView myusername = (TextView)view.findViewById(R.id.myusername);
        TextView exit = (TextView)view.findViewById(R.id.exit);
        butzhuxiao=(TextView)view.findViewById(R.id.butzhuxiao);
        exit.setText("退出");
        myusername.setText(MainActivity.userInfo.getUsername());
        tv_nave1 = (ImageButton)view.findViewById(R.id.tv_nave1);
        tv_nave2 = (ImageButton)view.findViewById(R.id.tv_nave2);
        tv_nave3 = (ImageButton)view.findViewById(R.id.tv_nave3);
        tv_nave1.setOnClickListener(this);
        tv_nave2.setOnClickListener(this);
        tv_nave3.setOnClickListener(this);
        butzhuxiao.setOnClickListener(this);
        exit.setOnClickListener(this);
        userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
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
                case R.id.tv_nave1:
                    Intent intent = new Intent(getActivity(),MyCommentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user",MainActivity.userInfo);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.tv_nave2:
                    Intent intent2 = new Intent(getActivity(),MyOrderActivity.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putSerializable("user",MainActivity.userInfo);
                    intent2.putExtras(bundle2);
                    startActivity(intent2);
                    break;
                case R.id.tv_nave3:
                    Intent intent3 = new Intent(getActivity(),MyCollectionActivity.class);
                    Bundle bundle3 = new Bundle();
                    bundle3.putSerializable("user",MainActivity.userInfo);
                    intent3.putExtras(bundle3);
                    startActivity(intent3);
                    break;
                case R.id.exit:
                    BmobUser.logOut(getActivity().getApplicationContext());
                    MainActivity.userInfo = BmobUser.getCurrentUser(getActivity().getApplicationContext(),MyUser.class);
                    Intent intent4 = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent4);
                    break;
                case  R.id.butzhuxiao:
                    Intent intent5 = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent5);
                    break;
            }
    }
}
