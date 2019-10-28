package com.sunshine.first.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunshine.first.R;
import com.sunshine.first.bean.AllIndentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayMoneyAdapter extends RecyclerView.Adapter<PayMoneyAdapter.ViewHolder> {
    private Context context;
    private List<AllIndentBean.DataBean.ListBean> list = new ArrayList<>();
    public PayMoneyAdapter(Context context) {
        this.context = context;
    }
    public void setDataList(List<AllIndentBean.DataBean.ListBean> listBean) {
        this.list = listBean;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PayMoneyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PayMoneyAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_all_indent,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PayMoneyAdapter.ViewHolder viewHolder, int i) {
        final AllIndentBean.DataBean.ListBean listBean = list.get(i);
        viewHolder.tvDescript.setText(listBean.getGoods_describe());
        viewHolder.tvCount.setText(listBean.getGoods_num()+"");
        viewHolder.tvPrice.setText(listBean.getMoney()+"");
        Glide.with(context).load(listBean.getGoods_image()).into(viewHolder.iconAllIndent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_descript)
        TextView tvDescript;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.icon_all_indent)
        ImageView iconAllIndent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
