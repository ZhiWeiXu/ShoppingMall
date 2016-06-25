package com.view.activity;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.data.domain.MyUser;
import com.view.widget.MyFragmentPagerAdapter;
import com.view.widget.PercentLinearLayout;
import com.view.widget.ViewPageAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends FragmentActivity {
    private TabLayout tabs;
    private ViewPager viewpage;
    private ViewPageAdapter MyViewPageAdapter = null;
    private List<Fragment> list = null;
    private MyFragmentPagerAdapter adapter = null;
    private List<String> titles = null;
    private int[] tabIcons = {R.drawable.homepage,
            R.drawable.shoppingcart,
            R.drawable.user};
    private TextView text_title;
    private DrawerLayout drawerLayout;
    private Handler handler = null;
    public static MyUser userInfo = null;
    private int[] meun_icon = {R.drawable.cleansing,R.drawable.cream,R.drawable.essence,
            R.drawable.emulsion,R.drawable.facialmask,R.drawable.lipstick};
    private String content[] = {"洁面","面霜","精华","乳液","面膜","唇膏"};
    private List<Map<String,Object>> mapList = null;
    private Map<String,Object> map;
    private ListView menvlistview;
    private PercentLinearLayout menu_list_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menulayout);
        Bmob.initialize(this,"4036b8cd09aa92590227e2d0d61507a4");
        userInfo = BmobUser.getCurrentUser(getApplicationContext(),MyUser.class);
        menvlistview = (ListView)findViewById(R.id.meun_listview) ;
        drawerLayout = (DrawerLayout)findViewById(R.id.drawlayout);
        menu_list_layout = (PercentLinearLayout)findViewById(R.id.menu_list_layout);
        mapList = new ArrayList<Map<String,Object>>();
        for(int i=0;i<content.length;i++){
            map = new HashMap<String,Object>();
            map.put("icon",meun_icon[i]);
            map.put("content",content[i]);
            mapList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this,mapList,R.layout.menu_list,
                new String[]{"icon","content"},new int[]{R.id.nav_icon,R.id.nav_content});
        menvlistview.setAdapter(simpleAdapter);
        ImageView imageView = (ImageView)findViewById(R.id.nav);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        menvlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Grinview_info.class);
                intent.putExtra("id", "" + position);
                startActivity(intent);
                drawerLayout.closeDrawer(menu_list_layout);
            }
        });

        tabs = (TabLayout) findViewById(R.id.tabs);
        viewpage = (ViewPager) findViewById(R.id.viewpage);
        text_title = (TextView)findViewById(R.id.text_title);
        list = new ArrayList<Fragment>();
        list.add(new HomePage());
        list.add(new ShoppingCartActivity());
        list.add(new PersonalCenter());
        titles = new ArrayList<String>();
        titles.add("主页");
        titles.add("购物车");
        titles.add("个人中心");
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),list,titles);
        viewpage.setAdapter(adapter);
        adapter.notifyDataSetChanged();
       for(String title : titles){
           tabs.newTab().setText(title);
       }
        //将viewpage和tablayout关联
        tabs.setupWithViewPager(viewpage);
        tabs.setTabsFromPagerAdapter(adapter);
        //设置刚加载时第一个tab是被选中的
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                    if(tab == tabs.getTabAt(1)){
                        if(userInfo==null) {
                            tabs.getTabAt(0).select();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            viewpage.setCurrentItem(1);
                        }
                    }
                    if(tab == tabs.getTabAt(2)){
                        if(userInfo==null) {
                            tabs.getTabAt(0).select();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            viewpage.setCurrentItem(2);
                        }
                    }
                if(tab == tabs.getTabAt(0)){
                    viewpage.setCurrentItem(0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        setupTabIcons();
    }
    private void setupTabIcons() {
        tabs.getTabAt(0).setCustomView(getTabView(0));
        tabs.getTabAt(1).setCustomView(getTabView(1));
        tabs.getTabAt(2).setCustomView(getTabView(2));
    }
    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
         text_title = (TextView) view.findViewById(R.id.text_title);
        text_title.setText(titles.get(position));
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        img_title.setImageResource(tabIcons[position]);
        return view;
    }

}
