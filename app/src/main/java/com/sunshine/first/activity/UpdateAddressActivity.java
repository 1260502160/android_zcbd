package com.sunshine.first.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.AddressDetailBean;

/**
 * 修改或者添加 地址详情信息
 */
public class UpdateAddressActivity extends BaseAppCompatActivity {
    /**
     * @param activity
     * @param id//地址id
     */
    public static void startActivity(Context activity, int id) {
        Intent intent = new Intent(activity, UpdateAddressActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_shopping_address;
    }

    @Override
    protected void initView() {
        setDefaultTitle("编辑收货地址");
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        hashMap.put("token", getToken());
        hashMap.put("id", id + "");
        net(true, false).post(1, Api.GetAddressDetailURL, hashMap);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1 && !TextUtils.isEmpty(data)) {
            AddressDetailBean addressDetailBean = gson.fromJson(data, AddressDetailBean.class);
            if (addressDetailBean != null && addressDetailBean.data != null) {

            }
        }
    }
}
