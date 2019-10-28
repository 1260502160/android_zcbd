package com.sunshine.first.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.AddressDetailBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改或者添加 地址详情信息
 */
public class UpdateAddressActivity extends BaseAppCompatActivity {

    @BindView(R.id.et_name_add_address)
    EditText et_name_add_address;

    /**
     * @param activity
     * @param type//   0添加 1修改
     * @param id//地址id
     */
    public static void nextStartActivity(Context activity, int type, int id) {
        Intent intent = new Intent(activity, UpdateAddressActivity.class);
        intent.putExtra("type", type);
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
        int type = getIntent().getIntExtra("type", 0);
        if (type == 1) {

            int id = getIntent().getIntExtra("id", 0);
            hashMap.put("token", getToken());
            hashMap.put("id", id + "");
            net(true, false).post(1, Api.GetAddressDetailURL, hashMap);
        }

    }

    @OnClick(R.id.btn_save)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save://保存
                String name = et_name_add_address.getText().toString().trim();



//                hashMap.clear();
//                hashMap.put("token", getToken());
//                hashMap.put("name", );//收货人姓名
//                hashMap.put("mobile", );//手机号
//                hashMap.put("province_id", );//省id
//                hashMap.put("city_id", );//市id
//                hashMap.put("area_id", );//区id
//                hashMap.put("detail", );//详细地址
//                hashMap.put("id", );//地址id 修改用

                net(true, false).post(1, Api.GetAddressDetailURL, hashMap);
                break;
        }
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
