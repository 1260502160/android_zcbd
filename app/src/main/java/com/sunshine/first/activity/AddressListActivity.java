package com.sunshine.first.activity;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.adapter.AddressListAdapter;
import com.sunshine.first.bean.AddressListBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 收货地址
 */
public class AddressListActivity extends BaseAppCompatActivity {
    @BindView(R.id.rv_address_list)
    RecyclerView rv_address_list;
    @BindView(R.id.tv_add_address_list)
    TextView tv_add_address_list;
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

    @OnClick(R.id.tv_add_address_list)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_address_list://添加收货地址
                UpdateAddressActivity.nextStartActivity(this, 0, 0);
                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1 && !TextUtils.isEmpty(data)) {
            AddressListBean addressListBean = gson.fromJson(data, AddressListBean.class);
            if (addressListBean != null && addressListBean.data.list != null && addressListBean.data.list.size() > 0) {
                addressListAdapter.addData(addressListBean.data.list);
            }
        }
    }
}
