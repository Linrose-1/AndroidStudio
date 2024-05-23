package com.rose.order.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rose.order.R;
import com.rose.order.adapter.AdBannerAdapter;
import com.rose.order.adapter.ShopAdapter;
import com.rose.order.bean.ShopBean;
import com.rose.order.utils.Constant;
import com.rose.order.utils.JsonParse;
import com.rose.order.views.ShopListView;
import com.rose.order.views.ViewPagerIndicator;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopActivity extends AppCompatActivity {

    private TextView tv_back, tv_title; // 返回按钮和标题文本控件
    private ShopListView slv_list; // 自定义的商店列表视图
    private ShopAdapter adapter; // 商店适配器
    private RelativeLayout rl_title_bar; // 标题栏布局
    private ViewPager adPager; // ViewPager，用于广告轮播
    private ViewPagerIndicator vpi; // ViewPager的指示器
    private View adBannerLay; // 广告轮播的布局
    private AdBannerAdapter ada; // 广告轮播适配器
    public static final int MSG_AD_SLID = 1; // 广告轮播消息常量
    public static final int MSG_SHOP_OK = 2; // 店铺加载成功消息常量

    private MHandler mHandler;  // Handler用于处理消息

    protected long exitTime;    //记录第一次点击时的时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop); // 设置布局文件
        mHandler = new MHandler(); // 初始化Handler
        initData(); // 初始化数据
        init(); // 初始化界面控件
    }

    /**
     * 初始化界面控件
     */
    private void init() {
        // 初始化标题栏和返回按钮
        tv_back = findViewById(R.id.tv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("店铺"); // 设置标题文本为"店铺"
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.blue_color)); // 设置标题栏背景色为蓝色
        tv_back.setVisibility(View.GONE); // 隐藏返回按钮

        // 初始化商店列表和适配器
        slv_list = findViewById(R.id.slv_list);
        adapter = new ShopAdapter(this); // 创建适配器
        slv_list.setAdapter(adapter); // 设置适配器到列表视图

        // 初始化广告轮播和指示器
        adBannerLay = findViewById(R.id.adbanner_layout);
        adPager = findViewById(R.id.slidingAdvertBanner);
        vpi = findViewById(R.id.advert_indicator);

        //adapter.setLongClickable(false);
        slv_list.setLongClickable(false);      // 禁用长按事件
        ada = new AdBannerAdapter(getSupportFragmentManager()); // 创建广告轮播适配器
        adPager.setAdapter(ada); // 设置适配器到ViewPager

//        adapter.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
//            @Override
//            public void onPageSelected(int index){
//                if (ada.getSize() > 0){
//                    vpi.setCurrentPostion(index % ada.getSize());
//                }
//            }
//            @Override
//            public void onPageScrolled(int arg0,float arg1 ,int arg2){}
//            @Override
//            public  void onPageScrollStateChanged(int arg0){}
//        });
        // 为ViewPager添加页面更改监听器
        adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int index) {
                if (ada.getSize() > 0) {
                    vpi.setCurrentPostion(index % ada.getSize());
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            @Override
            public void onPageScrollStateChanged(int arg0) {}
        });
        // 调整广告栏的大小
        resetSize();
        // 开始广告自动轮播线程
        new AdAutoSlidThread().start();
    }
    class AdAutoSlidThread extends Thread{
        @Override
        public void run(){
            super.run();
            while (true){
                try {
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (mHandler != null)
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
            }
        }
    }

    // 初始化数据
    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE + Constant.REQUEST_SHOP_URL).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 网络请求失败的处理
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Message msg = new Message();
                msg.what = MSG_SHOP_OK;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
        });
    }

    /**
     * 事件捕获
     */
    class MHandler extends Handler{
        @Override
        public void dispatchMessage(Message msg){
            super.dispatchMessage(msg);
            switch (msg.what){
                case MSG_SHOP_OK:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        List<ShopBean> sbl = JsonParse.getInstance().getShopList(vlResult);
                        adapter.setData(sbl);
                        if (sbl != null){
                            if (sbl.size() > 0){
                                ada.setData(sbl);
                                vpi.setCount(sbl.size());
                                vpi.setCurrentPostion(0);
                            }
                        }
                    }
                    break;
                case MSG_AD_SLID:
                    if (ada.getCount() > 0){
                        adPager.setCurrentItem(adPager.getCurrentItem() + 1);
                    }
                    break;
            }
        }
    }

    /**
     * 计算控件大小
     */
    private void resetSize(){
        int sw = getScreenWidth();
        int adLheight = sw / 3;
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        adBannerLay.setLayoutParams(adlp);
    }

    /**
     * 获取屏幕宽度
     */
    public int getScreenWidth(){
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis() - exitTime)>2000){
                Toast.makeText(ShopActivity.this , "再按一次退出" , Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ShopActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
