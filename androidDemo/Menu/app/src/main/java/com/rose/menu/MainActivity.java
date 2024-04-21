package com.rose.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private LeftFragment leftFragment;
    private TextView tv_recommend,tv_must_buy;
    private  RightFragment rightFragment;
    private String[] names1 = {"爆款*皇家空军解开了就好了就开","豪华双人套餐","热销双人套餐"};
    private String[] sales1 = {"月售520 好评率100%","月售184 好评率68%","月售184 好评率68%"};
    private String[] prices1 = {"$44","$44","$44"};
    private int[] imgs1 = {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};

    private String[] names2 = {"爆款*皇家空军解开了就好了就开","豪华双人情侣套餐","热销双人套餐"};
    private String[] sales2 = {"月售520 好评率100%","月售184 好评率68%","月售184 好评率100 %"};
    private String[] prices2 = {"$44","$44","$44"};
    private int[] imgs2 = {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};

    private Map<String, List<FoodBean>> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        init();
        clickEvent();
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
//        fragmentManager = (FragmentManager) getFragmentManager();
        leftFragment = (LeftFragment) fragmentManager.findFragmentById(R.id.left);
        tv_recommend=leftFragment.getView().findViewById(R.id.tv_recommend);
        tv_must_buy=leftFragment.getView().findViewById(R.id.tv_must_buy);
    }

    private void setData() {
        map = new HashMap<>();
        List<FoodBean> list1 = new ArrayList<>();
        List<FoodBean> list2 = new ArrayList<>();
        for (int i = 0; i < names1.length; i++) {
            FoodBean bean = new FoodBean();
            bean.setName(names1[i]);
            bean.setSales(sales1[i]);
            bean.setPrice(prices1[i]);
            bean.setImg(imgs1[i]);
            list1.add(bean);
        }
        map.put("1",list1);

        for (int i = 0; i < names2.length; i++) {
            FoodBean bean = new FoodBean();
            bean.setName(names2[i]);
            bean.setSales(sales2[i]);
            bean.setPrice(prices2[i]);
            bean.setImg(imgs2[i]);
            list2.add(bean);
        }
        map.put("2",list2);
    }

    private void clickEvent() {
        tv_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchData(map.get("1"));
                tv_recommend.setBackgroundColor(Color.WHITE);
                tv_must_buy.setBackgroundResource(R.color.gray);
            }
        });

        tv_must_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchData(map.get("2"));
                tv_must_buy.setBackgroundColor(Color.WHITE);
                tv_recommend.setBackgroundResource(R.color.gray);
            }
        });
        switchData(map.get("1"));
    }

    private void switchData(List<FoodBean> list) {
//        fragmentManager = getFragmentManager();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        rightFragment = new RightFragment().getInstance(list);
        fragmentTransaction.replace(R.id.right,rightFragment);
        fragmentTransaction.commit();
    }
}