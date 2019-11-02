package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
物业费
 */
public class PropertyChargesActivity extends BaseAppCompatActivity{
    @BindView(R.id.wexin_check_balance)
    RadioButton wexinCheckBalance;
    @BindView(R.id.zfpay_check_balance)
    RadioButton zfpayCheckBalance;
    @BindView(R.id.btn_rightpay)
    Button btnRightpay;
    private Intent intent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_property_fee;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setDefaultTitle("物业费缴纳");
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.wexin_check_balance, R.id.zfpay_check_balance,R.id.btn_rightpay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wexin_check_balance:
                break;
            case R.id.zfpay_check_balance:
                break;
            case R.id.btn_rightpay:
                intent = new Intent(PropertyChargesActivity.this, PaySucceActivity.class);
                startActivity(intent);
                break;
        }
    }
}
