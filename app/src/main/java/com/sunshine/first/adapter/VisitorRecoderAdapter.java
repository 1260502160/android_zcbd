package com.sunshine.first.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunshine.first.R;
import com.sunshine.first.activity.VisitorInvitationActivity;
import com.sunshine.first.activity.VisitorRegistrationActivity;
import com.sunshine.first.bean.GetCarListBean;
import com.sunshine.first.bean.GoodsListBean;
import com.sunshine.first.bean.VisitorRecodBean;

import java.util.ArrayList;
import java.util.List;

public class VisitorRecoderAdapter extends RecyclerView.Adapter<VisitorRecoderAdapter.ViewHolder> {

    private List<VisitorRecodBean.DataBean.ListBean> list = new ArrayList<>();
    private Context context;

    public VisitorRecoderAdapter(FragmentActivity activity) {
        this.context=activity;
    }
    @NonNull
    @Override
    public VisitorRecoderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VisitorRecoderAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_visitor_record,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorRecoderAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_indent_number.setText(list.get(i).getEnd_time());
        viewHolder.tv_descript.setText(list.get(i).getVisi_name());
        viewHolder.tv_phone.setText(list.get(i).getResidents_mobile());
        viewHolder.tv_address.setText(list.get(i).getCar_num());
        Glide.with(context).load(list.get(i).getVisi_imgs()).into(viewHolder.icon_all_indent);
        viewHolder.btn_ivtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, VisitorInvitationActivity.class);
                context.startActivity(intent);
            }
        });

        viewHolder.btn_ivtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, VisitorRegistrationActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setDataList(List<VisitorRecodBean.DataBean.ListBean> listBean) {
        this.list = listBean;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_indent_number,tv_descript,tv_phone,tv_address,tv_money;
        private ImageView icon_all_indent;
        private Button btn_ivtion,btn_again_ivation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_indent_number = itemView.findViewById(R.id.tv_indent_number);
            icon_all_indent = itemView.findViewById(R.id.icon_all_indent);
            tv_descript = itemView.findViewById(R.id.tv_descript);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_money = itemView.findViewById(R.id.tv_money);
            btn_ivtion = itemView.findViewById(R.id.btn_ivtion);
            btn_again_ivation = itemView.findViewById(R.id.btn_again_ivation);
            tv_money = itemView.findViewById(R.id.tv_money);


        }
    }
}
