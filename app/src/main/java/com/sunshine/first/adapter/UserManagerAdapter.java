package com.sunshine.first.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunshine.first.R;
import com.sunshine.first.activity.FangkeActivity;
import com.sunshine.first.activity.HouseHoldIdentity;
import com.sunshine.first.activity.IdentityAdfitActivity;
import com.sunshine.first.bean.HouseListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 住户管理 Adapter
 */
public class UserManagerAdapter extends RecyclerView.Adapter<UserManagerAdapter.UserManagerViewHolder> {
    private Context context;
    private List<HouseListBean.DataListBean> houseListBeanData;

    public UserManagerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<HouseListBean.DataListBean> houseListBeanData) {
        this.houseListBeanData = houseListBeanData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserManagerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserManagerViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_home_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserManagerViewHolder holder, final int position) {
        final HouseListBean.DataListBean dataBean = houseListBeanData.get(position);
        if (dataBean != null) {

            holder.tv_name_home_list.setText(dataBean.getCommunity_name() + "");
            holder.tv_address_home_list.setText(dataBean.getBuilding_name() + dataBean.getUnitdoor_name() + dataBean.getFloors_name() + "");

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int type = dataBean.getType();
                if (1 == type) {//房主
                    Intent intent = new Intent(context, HouseHoldIdentity.class);
                    context.startActivity(intent);
                } else {//房客

                    Intent intent = new Intent(context, FangkeActivity.class);
                    intent.putExtra("id", houseListBeanData.get(position).getId());
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return houseListBeanData == null ? 0 : houseListBeanData.size();
    }

    class UserManagerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_home_list)
        TextView tv_name_home_list;
        @BindView(R.id.tv_address_home_list)
        TextView tv_address_home_list;

        public UserManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
