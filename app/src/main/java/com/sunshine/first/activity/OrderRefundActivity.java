package com.sunshine.first.activity;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

/**
 * 订单;
 * 已退款
 */
public class OrderRefundActivity extends BaseAppCompatActivity {

    private int orderId;//订单id

    @Override
    public int getLayoutId() {
        return R.layout.layout_indent_deatil_back;
    }

    @Override
    protected void initView() {

        orderId = getIntent().getIntExtra("id", 0);
    }

    @Override
    protected void initData() {

    }

    private void getAddressArray() {
        hashMap.clear();
        hashMap.put("token", getToken());
        hashMap.put("id", orderId + "");
        net(true, false).post(1, Api.GetGoodsOrderDetail_URL, hashMap);
    }
}
