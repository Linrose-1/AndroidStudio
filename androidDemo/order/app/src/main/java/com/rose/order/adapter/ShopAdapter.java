package com.rose.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rose.order.R;
import com.rose.order.bean.ShopBean;

import java.util.List;

public class ShopAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopBean> sb1;
    public ShopAdapter(Context context){
        this.mContext = context;
    }

    /**
     * 获取数据并更新界面
     */
    public void setData(List<ShopBean> sb1){
        this.sb1 = sb1;
        notifyDataSetChanged();
    }

    /**
     * 获取条目总数
     */
    @Override
    public int getCount() {
        return sb1 == null ? 0 : sb1.size();
    }

    /**
     *获取position得到对应的条目对象
     */
    @Override
    public ShopBean getItem(int position) {
        return sb1 == null ? null : sb1.get(position);
    }
    /**
     *获取position得到对应的条目对象的id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * position 是当前条目的位置，convertView 是滚出屏幕的条目视图，parent 是当前视图组。
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;

        // 判断convertView是否为空，如果为空则需要创建新的视图
        if (convertView == null) {
            vh = new ViewHolder(); // 创建ViewHolder实例

            // 使用LayoutInflater从布局文件shop_item中创建新的视图
            convertView = LayoutInflater.from(mContext).inflate(R.layout.shop_item, null);

            // 通过findViewById找到布局中的控件，并将它们保存在ViewHolder中
            vh.tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
            vh.tv_sale_num = convertView.findViewById(R.id.tv_sale_num);
            vh.tv_cost = convertView.findViewById(R.id.tv_cost);
            vh.tv_feature = convertView.findViewById(R.id.tv_feature);
            vh.tv_time = convertView.findViewById(R.id.tv_time);
            vh.iv_shop_pic = convertView.findViewById(R.id.iv_shop_pic);

            // 将ViewHolder保存在convertView中，以便复用
            convertView.setTag(vh);
        } else {
            // 如果convertView不为空，则直接获取ViewHolder
            vh = (ViewHolder) convertView.getTag();
        }

        // 获取当前position对应的ShopBean对象
        final ShopBean bean = (ShopBean) getItem(position);

        // 如果bean不为空，则将数据绑定到视图
        if (bean != null) {
            // 设置商店名称
            vh.tv_shop_name.setText(bean.getShopName());

            // 设置月销售数量
            vh.tv_sale_num.setText("月售" + bean.getSaleNum());

            // 设置起送价格和配送费用
            vh.tv_cost.setText("起送￥" + bean.getOfferPrice() + " | 配送￥" + bean.getDistributionCost());

            // 设置配送时间
            vh.tv_time.setText(bean.getTime());

            // 设置商店特色
            vh.tv_feature.setText(bean.getFeature());

            // 使用Glide加载商店图片，如果加载失败则显示默认图片
            Glide.with(mContext)
                    .load(bean.getShopPic())
                    .error(R.mipmap.ic_launcher)
                    .into(vh.iv_shop_pic);
        }

        // 设置条目点击事件监听器，可以在此处理点击逻辑
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click
            }
        });

        // 返回当前条目的视图
        return convertView;
    }

    /**
     * ViewHolder类用于缓存条目视图中的子视图
     */
    class ViewHolder {
        public TextView tv_shop_name, tv_sale_num, tv_cost, tv_feature, tv_time;
        public ImageView iv_shop_pic;
    }

}
