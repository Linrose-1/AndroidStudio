package com.rose.order.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.rose.order.R;
import com.rose.order.bean.ShopBean;

public class AdBannerFragment extends Fragment {
    private ShopBean sb;
    private ImageView iv;


    public static AdBannerFragment newInstance(Bundle args) {
        AdBannerFragment af = new AdBannerFragment();
        af.setArguments(args);
        return af;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        sb = (ShopBean) arg.getSerializable("ad");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        if (sb != null){
            Glide
                    .with(getActivity())
                    .load(sb.getBanner())
                    .error(R.mipmap.ic_launcher)
                    .into(iv);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        iv = new ImageView(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return iv;
    }
}
