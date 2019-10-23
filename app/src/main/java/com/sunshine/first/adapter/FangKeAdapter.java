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
import com.sunshine.first.activity.FangkeActivity;
import com.sunshine.first.activity.HouseHoldIdentity;
import com.sunshine.first.bean.GetResidentsListBean;
import com.sunshine.first.bean.HouseListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FangKeAdapter extends RecyclerView.Adapter<FangKeAdapter.ViewHolder> {
    private Context context;
    private List<GetResidentsListBean.DataBean> residentsListBeanData;
    private int type;

    public FangKeAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<GetResidentsListBean.DataBean> residentsListBeanData) {
        this.residentsListBeanData = residentsListBeanData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FangKeAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_authenticated, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final GetResidentsListBean.DataBean dataBean = residentsListBeanData.get(i);
        if (dataBean != null) {

            viewHolder.textName.setText(dataBean.getResidents_name());
            viewHolder.tvPhone.setText(dataBean.getResidents_mobile()+"");
            type = dataBean.getType();
            if (type==1){
                viewHolder.tvSf.setText("房主");
            }else if(type==2){
                viewHolder.tvSf.setText("租客");
            }else if(type==4){
                viewHolder.tvSf.setText("家属");
            }


        }

    }

    @Override
    public int getItemCount() {
        return residentsListBeanData == null ? 0 : residentsListBeanData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_sf)
        TextView tvSf;
        @BindView(R.id.icon_authenticaed)
        ImageView iconAuthenticaed;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
