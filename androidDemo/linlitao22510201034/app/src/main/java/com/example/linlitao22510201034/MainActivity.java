package com.example.linlitao22510201034;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[]  titles = {"范德萨范德萨调查第三方地方地方","事故发生高富帅的个人风格范德萨官方","对他十分广泛的公司风格士大夫的反对","分的高分是德国发生过夫是德国发","分的高分是德国发生过夫是德国发","分的高分是德国发生过夫是德国发"};
    private String[] names = {"央视新闻客户端","味道食记","民福健康福","生活小计","禾木报告","燕鸣"};
    private String[] comments = {"465456","546","456465","545","456","5445"};
    private String[] times = {"6小时前","刚刚","1小时前","8小时前","6小时前","87小时前"};
    private int[] icons1 = {R.drawable.food,R.drawable.takeout,R.drawable.e_sports};
    private int[] icons2 = {R.drawable.sleep1,R.drawable.sleep2,R.drawable.sleep3,R.drawable.fruit1,R.drawable.fruit2,R.drawable.fruit3};

    private int[] types = {1,1,2,1,2,1};
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private List<NewBean> NewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();
        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewsAdapter(MainActivity.this ,NewList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setData() {
        NewList = new ArrayList<NewBean>();
        NewBean bean;
        for (int i = 0; i < titles.length; i++) {
            bean = new NewBean();
            bean.setId(i + 1);
            bean.setTitle(titles[i]);
            bean.setName(names[i]);
            bean.setComment(comments[i]);
            bean.setTime(times[i]);
            bean.setType(types[i]);
            switch (i) {
                case 0:
                    List<Integer> imgList0 = new ArrayList<>();
                    bean.setImgList(imgList0);
                    break;
                case 1:
                    List<Integer> imgList1 = new ArrayList<>();
                    imgList1.add(icons1[i-1]);
                    bean.setImgList(imgList1);
                    break;
                case 2:
                    List<Integer> imgList2 = new ArrayList<>();
                    imgList2.add(icons2[i-2]);
                    imgList2.add(icons2[i-1]);
                    imgList2.add(icons2[i]);
                    bean.setImgList(imgList2);
                    break;
                case 3:
                    List<Integer> imgList3 = new ArrayList<>();
                    imgList3.add(icons1[i-2]);
                    bean.setImgList(imgList3);
                    break;
                case 4:
                    List<Integer> imgList4 = new ArrayList<>();
                    imgList4.add(icons2[i-1]);
                    imgList4.add(icons2[i]);
                    imgList4.add(icons2[i+1]);
                    bean.setImgList(imgList4);
                    break;
                case 5:
                    List<Integer> imgList5 = new ArrayList<>();
                    imgList5.add(icons1[i-3]);
                    bean.setImgList(imgList5);
                    break;
            }
            NewList.add(bean);
        }
    }
}