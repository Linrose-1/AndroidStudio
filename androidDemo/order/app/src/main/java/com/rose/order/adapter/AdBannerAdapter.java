package com.rose.order.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rose.order.bean.ShopBean;
import com.rose.order.fragment.AdBannerFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdBannerAdapter extends FragmentStatePagerAdapter {
    private List<ShopBean> sb1;
    public AdBannerAdapter(FragmentManager fm) {
        super(fm);
        sb1 = new ArrayList<>();
    }

    /**
     *获取数据并更新页面
     */
    public void setData(List<ShopBean> sb1){
        this.sb1 = sb1;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int index) {
        Bundle args = new Bundle();
        if (sb1.size() > 0)
            args.putSerializable("ad",sb1.get(index % sb1.size()));
        return AdBannerFragment.newInstance(args);
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 返回数据集中元素的数量
     */
    public int getSize(){
        return sb1 == null ? 0:sb1.size();
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
}
