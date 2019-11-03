package com.sunshine.first.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunshine.first.R;
import com.sunshine.first.bean.HouseListBean;

import java.util.List;

/**
 * @author dell
 * @CreateDate: 2019-11-03 15:02
 * @Description:
 * @Version: 1.0
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {
    private Context context;
    private List<HouseListBean.DataListBean> houseListBeanData;

    public TextAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<HouseListBean.DataListBean> houseListBeanData) {
        this.houseListBeanData = houseListBeanData;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public TextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TextAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.my_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextAdapter.ViewHolder viewHolder, int i) {
        final HouseListBean.DataListBean dataBean = houseListBeanData.get(i);
        if (dataBean != null) {

            viewHolder.cummiunty_name.setText(dataBean.getCommunity_name() + "");
            viewHolder.home_name.setText(dataBean.getBuilding_name() + dataBean.getUnitdoor_name() + dataBean.getFloors_name() + "");

        }

    }

    @Override
    public int getItemCount() {
        return houseListBeanData == null ? 0 : houseListBeanData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cummiunty_name,home_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cummiunty_name = itemView.findViewById(R.id.cummiunty_name);
            home_name = itemView.findViewById(R.id.home_name);

        }
    }
}

