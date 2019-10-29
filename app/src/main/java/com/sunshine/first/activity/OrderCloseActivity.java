package com.sunshine.first.activity;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

/**
 * 已关闭页面
 */
public class OrderCloseActivity extends BaseAppCompatActivity {

    private int orderId;//订单id

    @Override
    public int getLayoutId() {
        return R.layout.layout_indent_close;
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
        hashMap.put("id", orderId+"");
        net(true, false).post(1, Api.GetGoodsOrderDetail_URL, hashMap);
    }
}
