package com.sunshine.first.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunshine.first.R;
import com.sunshine.first.bean.GetCarListBean;
import com.sunshine.first.bean.GoodsListBean;

import java.util.ArrayList;
import java.util.List;

public class GetCarListAdapter extends RecyclerView.Adapter<GetCarListAdapter.Viewholder> {


    private List<GetCarListBean.DataBean> list = new ArrayList<>();
    private Context context;

    public GetCarListAdapter(FragmentActivity activity) {
        this.context=activity;
    }
    @NonNull
    @Override
    public GetCarListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GetCarListAdapter.Viewholder(LayoutInflater.from(context).inflate(R.layout.adapter_getcarlist,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GetCarListAdapter.Viewholder viewholder, int i) {

        viewholder.tv_hosingname.setText(list.get(i).getCommunity_name());
        viewholder.tv_carnamber.setText(list.get(i).getPlate_num()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setDataList(List<GetCarListBean.DataBean> listBean) {
        this.list = listBean;
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private  TextView tv_hosingname,tv_carnamber;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_hosingname = itemView.findViewById(R.id.tv_hosingname);
            tv_carnamber = itemView.findViewById(R.id.tv_carnamber);

        }
    }
}
