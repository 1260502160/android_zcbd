package com.sunshine.first.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunshine.first.R;
import com.sunshine.first.activity.UserMangerActivity;
import com.sunshine.first.bean.GetResidentsListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HostManAdapter extends RecyclerView.Adapter<HostManAdapter.Viewholder> {
    private Context context;
    private List<GetResidentsListBean.DataBean> residentsListBeanData;

    public HostManAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<GetResidentsListBean.DataBean> residentsListBeanData) {
        this.residentsListBeanData = residentsListBeanData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HostManAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HostManAdapter.Viewholder(LayoutInflater.from(context).inflate(R.layout.adapter_hostman, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HostManAdapter.Viewholder viewholder, final int i) {

        final GetResidentsListBean.DataBean dataBean = residentsListBeanData.get(i);
        if (dataBean != null) {

            viewholder.textName.setText(dataBean.getResidents_name());
            viewholder.tvPhone.setText(dataBean.getResidents_mobile() + "");
            viewholder.iconDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    residentsListBeanData.remove(i);
                    notifyDataSetChanged();
                    if (context instanceof UserMangerActivity) {
                        UserMangerActivity userMangerActivity = (UserMangerActivity) context;
                        userMangerActivity.deleUserInfo(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return residentsListBeanData == null ? 0 : residentsListBeanData.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_sf)
        TextView tvSf;
        @BindView(R.id.icon_authenticaed)
        ImageView iconAuthenticaed;
        @BindView(R.id.icon_del)
        ImageView iconDel;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
