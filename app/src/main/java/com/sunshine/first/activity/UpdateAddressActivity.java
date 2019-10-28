package com.sunshine.first.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.abner.ming.base.model.Api;
import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.AddAddressBean;
import com.sunshine.first.bean.AddressDetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改或者添加 地址详情信息
 */
public class UpdateAddressActivity extends BaseAppCompatActivity {

    @BindView(R.id.et_name_add_address)
    EditText et_name_add_address;
    @BindView(R.id.et_phone_number)
    EditText et_phone_number;
    @BindView(R.id.et_shopping_addre)
    EditText et_shopping_addre;
    @BindView(R.id.tv_details_address_details)
    TextView tv_details_address_details;

    private int type;
    private AddressDetailBean.AddressDetailListBean addressDetailListBean;

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
        type = getIntent().getIntExtra("type", 0);
        if (type == 1) {
            int id = getIntent().getIntExtra("id", 0);
            hashMap.clear();
            hashMap.put("token", getToken());
            hashMap.put("id", id + "");
            net(true, false).post(1, Api.GetAddressDetailURL, hashMap);
        }

    }

    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();
    private int provinceId, cityId, areaId;

    @OnClick({R.id.btn_save, R.id.relative_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_address://选择地址
                showProvince(new OnSelectIdName() {
                    @Override
                    public void onSelectIdName(int provinceId, String provinceName, int cityId, String cityName, int areaId, String areaName) {
                        tv_details_address_details.setText(provinceName + cityName + areaName + "");
                        UpdateAddressActivity.this.provinceId = provinceId;
                        UpdateAddressActivity.this.cityId = cityId;
                        UpdateAddressActivity.this.areaId = areaId;
                    }
                });
                break;
            case R.id.btn_save://保存
                String name = et_name_add_address.getText().toString().trim();
                String phone = et_phone_number.getText().toString().trim();
                String addressDetails = et_shopping_addre.getText().toString().trim();
                hashMap.clear();
                hashMap.put("token", getToken());
                hashMap.put("name", name);//收货人姓名
                hashMap.put("mobile", phone);//手机号
                hashMap.put("province_id", UpdateAddressActivity.this.provinceId + "");//省id
                hashMap.put("city_id", UpdateAddressActivity.this.cityId + "");//市id
                hashMap.put("area_id", UpdateAddressActivity.this.areaId + "");//区id
                hashMap.put("detail", addressDetails);//详细地址
                if (type == 1) {
                    hashMap.put("id", addressId + "");//地址id 修改用
                } else {
                }
                net(true, false).post(2, Api.ADD_AddressListURL, hashMap);
                break;
        }
    }

    private int addressId;

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1 && !TextUtils.isEmpty(data)) {
            AddressDetailBean addressDetailBean = gson.fromJson(data, AddressDetailBean.class);
            if (addressDetailBean != null && addressDetailBean.data != null) {
                addressDetailListBean = addressDetailBean.data;
                et_name_add_address.setText(addressDetailListBean.name + "");
                et_phone_number.setText(addressDetailListBean.mobile + "");
                et_shopping_addre.setText(addressDetailListBean.detail + "");

                UpdateAddressActivity.this.provinceId = addressDetailListBean.province_id;
                UpdateAddressActivity.this.cityId = addressDetailListBean.city_id;
                UpdateAddressActivity.this.areaId = addressDetailListBean.area_id;
                addressId = addressDetailListBean.id;
                tv_details_address_details.setText(addressDetailListBean.province_name + addressDetailListBean.city_name + addressDetailListBean.area_name + "");
            }
        } else if (type == 2) {
            AddAddressBean addressBean = gson.fromJson(data, AddAddressBean.class);
            ToastManage.s(this, addressBean.message + "");
            if (addressBean != null && "200".equals(addressBean.error_code)) {
                finish();
            }
        }
    }
}
