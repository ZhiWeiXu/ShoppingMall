package com.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.view.activity.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list = null;
    private List<String> titles = null;
    private int[] icons = null;
    private Context context;
    public MyFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> list, List<String> titles){
        super(fragmentManager);
        this.list = list;
        this.titles = titles;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return  titles.get(position);
    }

}
