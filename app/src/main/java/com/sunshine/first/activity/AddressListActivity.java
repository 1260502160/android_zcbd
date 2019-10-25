package com.sunshine.first.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.adapter.AddressListAdapter;
import com.sunshine.first.bean.AddressListBean;

import butterknife.BindView;

/**
 * 收货地址
 */
public class AddressListActivity extends BaseAppCompatActivity {
    @BindView(R.id.rv_address_list)
    RecyclerView rv_address_list;
    private AddressListAdapter addressListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_list;
    }

    @Override
    protected void initView() {
        setDefaultTitle("收货地址");

        rv_address_list.setLayoutManager(new LinearLayoutManager(this));
        addressListAdapter = new AddressListAdapter(this);
        rv_address_list.setAdapter(addressListAdapter);
    }

    @Override
    protected void initData() {
        hashMap.put("token", getToken());
        net(true, false).post(1, Api.GetAddressListURL, hashMap);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1 && !TextUtils.isEmpty(data)) {
            AddressListBean addressListBean = gson.fromJson(data, AddressListBean.class);
            if (addressListBean != null && addressListBean.data != null && addressListBean.data.size() > 0) {
                addressListAdapter.addData(addressListBean.data);
            }
        }
    }
}
