package com.sunshine.first.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunshine.first.R;
import com.sunshine.first.activity.HostmanRenActivity;
import com.sunshine.first.bean.GetCommunityBean;
import com.sunshine.first.bean.GoodsListBean;

import java.util.ArrayList;
import java.util.List;

public class ChooseCummintyAdapter extends RecyclerView.Adapter<ChooseCummintyAdapter.Viewholder> {

    private List<GetCommunityBean.DataBean> list = new ArrayList<>();
    private Context context;
    private Intent intent;

    public ChooseCummintyAdapter(FragmentActivity activity) {
        this.context=activity;
    }

    @NonNull
    @Override
    public ChooseCummintyAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ChooseCummintyAdapter.Viewholder(LayoutInflater.from(context).inflate(R.layout.adapter_tv_cummity,viewGroup,false));
    }


    @Override
    public void onBindViewHolder(@NonNull ChooseCummintyAdapter.Viewholder viewholder, final int i) {

        viewholder.tv_name_cumm.setText(list.get(i).getName());
        viewholder.tv_name_cumm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, HostmanRenActivity.class);
                intent.putExtra("id",list.get(i).getId());
                intent.putExtra("name",list.get(i).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setDataList(List<GetCommunityBean.DataBean> listBean) {
        this.list = listBean;
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder {


        private TextView tv_name_cumm;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_name_cumm = itemView.findViewById(R.id.tv_name_cumm);

        }
    }

    //首先定义一个接口
    public interface OnItemClickListener {
        void onItemClickListener(int type);
    }
    //声明接口
    private StoreAllAdapter.OnItemClickListener mClickListener;
    //供外部使用
    public void setOnItemClickListener(StoreAllAdapter.OnItemClickListener pListener) {
        this.mClickListener = pListener;
    }
}
