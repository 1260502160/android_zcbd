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
import com.sunshine.first.bean.GetRepairListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepairPayAdapter extends RecyclerView.Adapter<RepairPayAdapter.ViewHolder> {

    private Context context;
    private List<GetRepairListBean.DataBean.ListBean> repairListBeanData;
    private int type;

    public RepairPayAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<GetRepairListBean.DataBean.ListBean> repairListBeanData) {
        this.repairListBeanData = repairListBeanData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_repair_all, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final GetRepairListBean.DataBean.ListBean dataBean = repairListBeanData.get(i);
        if (dataBean!=null){
            viewHolder.tvDescript.setText(dataBean.getExplain());
            Glide.with(context).load(dataBean.getImg_url()).into(viewHolder.iconAllIndent);
            viewHolder.tvPayStatus.setText(dataBean.getPay_state());

        }
    }

    @Override
    public int getItemCount() {
        return repairListBeanData == null ? 0 : repairListBeanData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_descript)
        TextView tvDescript;
        @BindView(R.id.tv_pay_status)
        TextView tvPayStatus;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.icon_all_indent)
        ImageView iconAllIndent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
