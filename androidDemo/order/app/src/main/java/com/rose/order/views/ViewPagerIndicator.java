package com.rose.order.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.rose.order.R;

public class ViewPagerIndicator extends LinearLayout {
//    定义成员变量：
    private int mCount;
    private int mIndex;
    private Context context;

    /**
     *     构造函数：用于初始化 ViewPagerIndicator 实例。
     *     第一个构造函数是无参构造函数，调用了第二个构造函数并传递了 null 作为属性集合参数。
     *     第二个构造函数接受了一个上下文对象和一个属性集合对象。
     */
    public ViewPagerIndicator(Context context) {
        this(context,null);
    }

    public ViewPagerIndicator(Context context,AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    /**
     * setCurrentPostion(int currentIndex) 方法：
     * 设置滑动到当前小圆点时其他圆点的位置
     */
    public void setCurrentPostion(int currentIndex){
        mIndex = currentIndex;      //当前小圆点
        this.removeAllViews();      //移除界面上存在的view
        int pex = context.getResources().getDimensionPixelSize(
                R.dimen.view_indicator_padding);        //通过资源获取器获取指示器的内边距值，该值在 dimens.xml 文件中定义。
        //循环遍历指示器的总数量，根据数量创建对应数量的小圆点。
        for (int i = 0; i < this.mCount; i++) {
            //创建一个 ImageView 对象用于显示小圆点
            ImageView imageView = new ImageView(context);
            //判断当前循环的位置是否与当前页面位置相同。
            if (mIndex == i){
                //如果当前循环的位置与当前页面位置相同，则设置小圆点为白色。
                imageView.setImageResource(R.drawable.indicator_on);
            }else {
                //如果当前循环的位置与当前页面位置不同，则设置小圆点为黑色。
                imageView.setImageResource(R.drawable.indicator_off);
            }
            imageView.setPadding(pex , 0 ,pex , 0);     //设置小圆点的内边距，使其与其他小圆点有一定的间距。
            this.addView(imageView);        //将创建好的小圆点添加到指示器布局中。
        }
    }

    /**
     * setCount(int count) 方法：
     * 设置小圆点的数目
     */
    public void setCount(int count){
        this.mCount = count;
    }

}
