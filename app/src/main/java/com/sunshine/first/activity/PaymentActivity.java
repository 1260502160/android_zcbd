package com.sunshine.first.activity;

import android.content.Intent;
import android.view.View;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.OnClick;

/**
 * 购买支付页面
 */
public class PaymentActivity extends BaseAppCompatActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initView() {
        setDefaultTitle("支付");

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.rl_address)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_address://选择地址
                Intent intent = new Intent(this, AddressListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
