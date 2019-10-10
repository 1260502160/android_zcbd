package com.sunshine.first.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunshine.first.R;
import com.sunshine.first.activity.GoodsDetailActivity;
import com.sunshine.first.bean.GoodsListBean;

import java.util.ArrayList;
import java.util.List;

public class StoreAllAdapter extends RecyclerView.Adapter<StoreAllAdapter.Viewholder> {

    private List<GoodsListBean.DataBean.ListBean> list = new ArrayList<>();
    private Context context;
    private Intent intent;

    public StoreAllAdapter(FragmentActivity activity) {
        this.context=activity;
    }


    @NonNull
    @Override
    public StoreAllAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.adapter_hot_store,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAllAdapter.Viewholder viewholder, final int i) {
        Glide.with(context).load(list.get(i).getGoods_image()).into(viewholder.icongoods);
        viewholder.tv_store_name.setText(list.get(i).getGoods_describe());
        viewholder.tv_store_price.setText(list.get(i).getRetail_price()+"");
        viewholder.tv_trade_price.setText(list.get(i).getRetail_price()+"");

        viewholder.linea_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果想在activity中使用就用下面这个 在activity点击事件中设置点击事件   adapter。setOnItemClickListener
//                mClickListener.onItemClickListener(list.get(i));
                //执行你的跳转
               intent = new Intent(context, GoodsDetailActivity.class);
                int id = list.get(i).getId();
                intent.putExtra("id",id);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public void setDataList(List<GoodsListBean.DataBean.ListBean> listBean) {
        this.list = listBean;
        notifyDataSetChanged();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView icongoods;
        private TextView tv_store_name,tv_store_price,tv_trade_price;
        private LinearLayout linea_goods;


        public Viewholder(@NonNull final View itemView) {
            super(itemView);
            icongoods = itemView.findViewById(R.id.icon_goods);
            tv_store_name = itemView.findViewById(R.id.tv_store_name);
            tv_store_price = itemView.findViewById(R.id.tv_store_price);
            tv_trade_price = itemView.findViewById(R.id.tv_trade_price);
            linea_goods = itemView.findViewById(R.id.linear_goods);

        }
    }


    //首先定义一个接口
    public interface OnItemClickListener {
        void onItemClickListener(int type);
    }
    //声明接口
    private OnItemClickListener mClickListener;
    //供外部使用
    public void setOnItemClickListener(OnItemClickListener pListener) {
        this.mClickListener = pListener;
    }
}
