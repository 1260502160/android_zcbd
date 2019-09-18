package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.wexin_check_balance)
    RadioButton wexinCheckBalance;
    @BindView(R.id.zfpay_check_balance)
    RadioButton zfpayCheckBalance;
    @BindView(R.id.btn_rightpay)
    Button btnrightpay;
    private String payType = "1";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.wexin_check_balance, R.id.zfpay_check_balance, R.id.btn_rightpay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.wexin_check_balance:
                payType = "1";
                break;
            case R.id.zfpay_check_balance:
                payType = "2";
                break;
            case R.id.btn_rightpay:
                intent = new Intent(PayActivity.this, PaySucceActivity.class);
                startActivity(intent);
                break;
        }
    }
}
