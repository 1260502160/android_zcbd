package com.sunshine.first.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunshine.first.R;
import com.sunshine.first.activity.UpdateAddressActivity;
import com.sunshine.first.bean.AddressListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 地址列表适配器
 */
public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListHolder> {
    private Context mContext;
    private List<AddressListBean.ListAddressBean> data;

    public AddressListAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(List<AddressListBean.ListAddressBean> list) {
        this.data = list;
    }

    @NonNull
    @Override
    public AddressListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AddressListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_receive_address, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddressListHolder holder, int position) {
        final AddressListBean.ListAddressBean addressBean = data.get(position);
        if (addressBean != null) {
            holder.tv_name.setText(addressBean.name);
            holder.tv_phone_number.setText(addressBean.mobile);
            String address = addressBean.province_name + addressBean.city_name + addressBean.area_name + addressBean.detail + "";
            holder.tv_address_details.setText(address);
            holder.tv_update_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UpdateAddressActivity.startActivity(mContext, addressBean.id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class AddressListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_phone_number)
        TextView tv_phone_number;
        @BindView(R.id.tv_default)
        TextView tv_default;
        @BindView(R.id.tv_address_details)
        TextView tv_address_details;
        @BindView(R.id.tv_update_address)
        TextView tv_update_address;

        public AddressListHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
