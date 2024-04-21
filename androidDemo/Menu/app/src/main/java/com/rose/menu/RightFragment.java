package com.rose.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.List;

public class RightFragment extends Fragment {
    private ListView lv_list;

    public RightFragment() {
    }

    public RightFragment getInstance(List<FoodBean> list) {
        RightFragment rightFragment = new RightFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list",(Serializable) list);
        rightFragment.setArguments(bundle);
        return rightFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_layout,container,false);
        lv_list = view.findViewById(R.id.lv_list);
        Bundle arguments = getArguments();
        if (arguments != null) {
            List<FoodBean> list = (List<FoodBean>) getArguments().getSerializable("list");
            if (list != null) {
                RightAdapter adapter = new RightAdapter(getActivity(),list);
                lv_list.setAdapter(adapter);
            }
        }
        return view;
    }

}
