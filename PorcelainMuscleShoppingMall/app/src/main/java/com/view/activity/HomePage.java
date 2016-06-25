package com.view.activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.data.domain.AdDomain;
import com.data.domain.Commodity;
import com.data.domain.Homepage;
import com.logic.utils.LoadImagesUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

public class HomePage extends Fragment implements View.OnClickListener{
    public static String IMAGE_CACHE_PATH = "Porcelainmuscleshoppingmall/images"; // 图片缓存路径
    private List<ImageView> imageViews;// 滑动的图片集合
    private List<View> dots; // 图片标题正文的那些点
    private List<View> dotList;
    private TextView tv_date;
    private TextView tv_title;
    private TextView tv_topic_from;
    private int number;
    private TextView tv_topic;
    private ViewPager adViewPager;
    private List<String> url;
    private List<Commodity> commodityList = null;
    private int currentItem = 0; // 当前图片的索引号
    // 定义的五个指示点
    private View dot0;
    private View dot1;
    private View dot2;
    private View dot3;
    private View dot4;
    private View view;
    private int i = 0;
    private List<ImageView> imageViewList;
    private ScheduledExecutorService scheduledExecutorService;
    // 异步加载图片
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;
    // 轮播banner的数据
    private List<AdDomain> adList;
    private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img13,img14,img15,img16,img17,img18,img19,img20,img21,img22,img23;
    private String[] topurl = new String[]{"b8YP333U", "r6DL333Y", "up9VbbbR", "BNYmHHHO", "baoj6669"};
    private List<HashMap<String,String>> list ;
    private Message message = new Message();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            adViewPager.setCurrentItem(currentItem);
        };
    };
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.tab1,null);
        i = 0;
        number = 0;
        url = new ArrayList<String>();
        imageViewList =  new ArrayList<ImageView>();
        commodityList = new ArrayList<Commodity>();
        // 使用ImageLoader之前初始化
        initImageLoader();
        // 获取图片加载实例
        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.top_banner_android)
                .showImageForEmptyUri(R.drawable.top_banner_android)
                .showImageOnFail(R.drawable.top_banner_android)
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();
      //   selectdata(topurl);\\\
        img1 = (ImageView)view.findViewById(R.id.img1);
        img2 = (ImageView)view.findViewById(R.id.img2);
        img3 = (ImageView)view.findViewById(R.id.img3);
        img4 = (ImageView)view.findViewById(R.id.img4);
        img5 = (ImageView)view.findViewById(R.id.img5);
        img6 = (ImageView)view.findViewById(R.id.img6);
         img7 = (ImageView)view.findViewById(R.id.img7);
         img8 = (ImageView)view.findViewById(R.id.img8);
         img9 = (ImageView)view.findViewById(R.id.img9);
         img10 = (ImageView)view.findViewById(R.id.img10);
         img13 = (ImageView)view.findViewById(R.id.img13);
        img14 = (ImageView)view.findViewById(R.id.img14);
        img15 = (ImageView)view.findViewById(R.id.img15);
        img16 = (ImageView)view.findViewById(R.id.img16);
        img17 = (ImageView)view.findViewById(R.id.img17);
        img18 = (ImageView)view.findViewById(R.id.img18);
        img19 = (ImageView)view.findViewById(R.id.img19);
        img20 = (ImageView)view.findViewById(R.id.img20);
        img21 = (ImageView)view.findViewById(R.id.img21);
        img22 = (ImageView)view.findViewById(R.id.img22);
        img23 = (ImageView)view.findViewById(R.id.img23);
        imageViewList.add(img7);
        imageViewList.add(img8);
        imageViewList.add(img9);
        imageViewList.add(img10);
        imageViewList.add(img13);
        imageViewList.add(img14);
        imageViewList.add(img15);
        imageViewList.add(img16);
        imageViewList.add(img17);
        imageViewList.add(img18);
        imageViewList.add(img14);
        imageViewList.add(img15);
        imageViewList.add(img16);
        imageViewList.add(img17);
        imageViewList.add(img18);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        img7.setOnClickListener(this);
        img8.setOnClickListener(this);
        img9.setOnClickListener(this);
        img10.setOnClickListener(this);
        img13.setOnClickListener(this);
        img14.setOnClickListener(this);
        img15.setOnClickListener(this);
        img16.setOnClickListener(this);
        img17.setOnClickListener(this);
        img18.setOnClickListener(this);
        img19.setOnClickListener(this);
        img20.setOnClickListener(this);
        img21.setOnClickListener(this);
        img22.setOnClickListener(this);
        img23.setOnClickListener(this);
        initAdData();
       startAd();

        selectdata();
        return view;
    }
    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
                .getOwnCacheDirectory( getContext(),
                        IMAGE_CACHE_PATH);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getContext()).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
    private void initAdData() {
        // 广告数据
        adList = getBannerAd();

        imageViews = new ArrayList<ImageView>();

        // 点
        dots = new ArrayList<View>();
        dotList = new ArrayList<View>();
        dot0 = view.findViewById(R.id.v_dot0);
        dot1 = view.findViewById(R.id.v_dot1);
        dot2 = view.findViewById(R.id.v_dot2);
        dot3 = view.findViewById(R.id.v_dot3);
        dot4 = view.findViewById(R.id.v_dot4);
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);

        tv_date = (TextView) view.findViewById(R.id.tv_date);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_topic_from = (TextView) view.findViewById(R.id.tv_topic_from);
        tv_topic = (TextView) view.findViewById(R.id.tv_topic);

        adViewPager = (ViewPager) view.findViewById(R.id.vp);
        adViewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        adViewPager.setOnPageChangeListener(new MyPageChangeListener());
        addDynamicView();
    }
    private void addDynamicView() {
        // 动态添加图片和下面指示的圆点
        // 初始化图片资源
        for (int i = 0; i < adList.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            // 异步加载图片
            mImageLoader.displayImage(adList.get(i).getImgUrl(), imageView,
                    options);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
            dots.get(i).setVisibility(View.VISIBLE);
            dotList.add(dots.get(i));
        }
    }
    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
                TimeUnit.SECONDS);
    }



    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (adViewPager) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget();

            }
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        // 当Activity不可见的时候停止切换
        scheduledExecutorService.shutdown();
    }
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            AdDomain adDomain = adList.get(position);
            tv_title.setText(adDomain.getTitle()); // 设置标题
            tv_date.setText(adDomain.getDate());
            tv_topic_from.setText(adDomain.getTopicFrom());
            tv_topic.setText(adDomain.getTopic());
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return adList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            ((ViewPager) container).addView(iv);
            final AdDomain adDomain = adList.get(position);
            // 在这个方法里面设置图片的点击事件
            iv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 处理跳转逻辑
                }
            });
            return iv;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }
    //加载数据
    public static List<AdDomain> getBannerAd() {
        List<AdDomain> adList = new ArrayList<AdDomain>();

        AdDomain adDomain = new AdDomain();

        adList.add(adDomain);

        AdDomain adDomain2 = new AdDomain();

        adDomain2
                .setImgUrl("http://7lrz6l.com1.z0.glb.clouddn.com/085805175306-110.jpg");

        adList.add(adDomain2);

        AdDomain adDomain3 = new AdDomain();
        adDomain3
                .setImgUrl("http://7lrz6l.com1.z0.glb.clouddn.com/tupian3.png");

        adList.add(adDomain3);

        AdDomain adDomain4 = new AdDomain();

        adDomain4
                .setImgUrl("http://7lrz6l.com1.z0.glb.clouddn.com/6926799605919-110.jpg");

        adList.add(adDomain4);

        AdDomain adDomain5 = new AdDomain();

        adDomain5
                .setImgUrl("http://7lrz6l.com1.z0.glb.clouddn.com/6958717866348-110_1.jpg");

        adList.add(adDomain5);

        return adList;
    }
//从后台获取相应的数据-----------------------------------------------------------------------------
    public void selectdata(){
        BmobQuery<Homepage> query = new BmobQuery<Homepage>();
        query.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);
        query.include("commodity,commodity.commodityUrl");
        query.setLimit(15);
        query.addWhereEqualTo("name", "maincontents");
        query.findObjects(getActivity(), new FindListener<Homepage>() {
            @Override
            public void onSuccess(List<Homepage> list) {
                if(list.size()!=0) {
                    for(Homepage homepage : list){
                        url.add(homepage.getURL1());
                        commodityList.add(homepage.getCommodity());
                        if(i<imageViewList.size())
                       new LoadImagesUtils(imageViewList.get(i++)).LoadImaegs(homepage.getURL1());
                    //  new Tupianjiazai().getPicture(homepage.getURL1(),imageViewList.get(i++));
                 //       Toast.makeText(getActivity(),String.valueOf(list.size()),Toast.LENGTH_LONG).show();
                    }
                }

            }
            @Override
            public void onError(int i, String s) {
            }
        });
    }
    public void onClick(View v) {
        int position=0;
        Intent intent1 = new Intent(getActivity(), Grinview_info.class);

        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        Bundle bundle = new Bundle();
        Commodity commodity;
        switch (v.getId()){
            case  R.id.img1:
                position=0;

                intent1.putExtra("id", "" + position);
                startActivity(intent1);
                break;
            case  R.id.img2:
                position=1;
                intent1.putExtra("id", "" + position);
                startActivity(intent1);
                break;
            case  R.id.img3:
                position=2;
                intent1.putExtra("id", "" + position);
                startActivity(intent1);
                break;
            case  R.id.img4:
                position=3;
                intent1.putExtra("id", "" + position);
                startActivity(intent1);
                break;
            case  R.id.img5:
                position=4;
                intent1.putExtra("id", "" + position);
                startActivity(intent1);
                break;
            case  R.id.img6:
                position=5;
                intent1.putExtra("id", "" + position);
                startActivity(intent1);
                break;
            case R.id.img7:
                if (commodityList.size()!=0) {
                    commodity = commodityList.get(0);
                    bundle.putSerializable("commodity", commodity);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.img8:

                 commodity = commodityList.get(1);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img9:
                 commodity = commodityList.get(2);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img10:
                commodity = commodityList.get(3);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img13 :
                commodity = commodityList.get(4);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img14 :
                commodity = commodityList.get(5);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img15 :
                commodity = commodityList.get(6);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img16 :
                commodity = commodityList.get(7);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img17 :
                commodity = commodityList.get(8);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img18 :
                commodity = commodityList.get(9);
                bundle.putSerializable("commodity", commodity);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img19 :

                commodity = commodityList.get(10);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img20 :
                commodity = commodityList.get(11);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img21 :
                commodity = commodityList.get(12);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img22 :
                commodity = commodityList.get(13);
                bundle.putSerializable("commodity", commodity);

                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.img23 :
                commodity = commodityList.get(14);
                bundle.putSerializable("commodity", commodity);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
