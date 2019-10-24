package com.sunshine.first.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunshine.first.R;
import com.sunshine.first.activity.GoodsDetailActivity;
import com.sunshine.first.bean.GoodsListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品列表适配器
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder> {
    private Context mContext;
    private List<GoodsListBean.DataBean.ListBean> data = new ArrayList<>();

    public GoodsAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<GoodsListBean.DataBean.ListBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<GoodsListBean.DataBean.ListBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GoodsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_hot_store, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        final GoodsListBean.DataBean.ListBean listBean = data.get(position);
        if (listBean != null) {
            holder.tv_store_name.setText(listBean.getGoods_describe());
            holder.tv_store_price.setText(listBean.getWholesale_price());
            holder.tv_trade_price.setText(listBean.getRetail_price());
            Glide.with(mContext).load(listBean.getGoods_image()).into(holder.icon_goods);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                    intent.putExtra("id", listBean.getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon_goods)
        ImageView icon_goods;
        @BindView(R.id.tv_store_name)
        TextView tv_store_name;
        @BindView(R.id.tv_store_price)
        TextView tv_store_price;
        @BindView(R.id.tv_trade_price)
        TextView tv_trade_price;

        public GoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
