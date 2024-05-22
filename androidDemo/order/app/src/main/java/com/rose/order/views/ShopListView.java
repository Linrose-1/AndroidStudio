package com.rose.order.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ShopListView extends ListView {
    //构造函数，用于在代码中创建 ShopListView 实例时调用
    public ShopListView(Context context) {
        super(context);
    }
    //构造函数，用于在 XML 布局文件中声明 ShopListView 时调用。
    public ShopListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //构造函数，用于在 XML 布局文件中声明 ShopListView 且指定样式时调用。
    public ShopListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override       //该注解表示方法是对父类方法的重写
    //重写 onMeasure 方法，该方法用于测量 View 的大小。
    protected void onMeasure(int widthMeasureSpec ,int heighttMeasureSpec) {
        //创建一个新的测量规范，指定 View 的高度为无限大。Integer.MAX_VALUE >> 2 表示一个足够大的值，MeasureSpec.AT_MOST 表示 View 的大小可以是指定大小或者更小。
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2 ,MeasureSpec.AT_MOST);
        //调用父类的 onMeasure 方法，并将宽度的测量规范保持不变，将高度的测量规范替换为上面创建的 expandSpec，从而实现了高度可以无限扩展的效果。
        super.onMeasure(widthMeasureSpec , expandSpec);
    }
}
