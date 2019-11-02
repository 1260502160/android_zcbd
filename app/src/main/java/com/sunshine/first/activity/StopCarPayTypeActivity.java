package com.sunshine.first.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.ParkingChargeBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StopCarPayTypeActivity extends BaseAppCompatActivity {
    @BindView(R.id.text_carnumber)
    TextView textCarnumber;
    @BindView(R.id.view_stopcar_one)
    View viewStopcarOne;
    @BindView(R.id.text_car_number)
    TextView textCarNumber;
    @BindView(R.id.icon_carnumber)
    ImageView iconCarnumber;
    @BindView(R.id.text_yucunzhouqi)
    TextView textYucunzhouqi;
    @BindView(R.id.text_yczhouqi)
    TextView textYczhouqi;
    @BindView(R.id.icon_yczhouqi)
    ImageView iconYczhouqi;
    @BindView(R.id.rela_paymoney)
    RelativeLayout relaPaymoney;
    @BindView(R.id.view_pay_money)
    View viewPayMoney;
    @BindView(R.id.wx)
    TextView wx;
    @BindView(R.id.wexin_check_balance)
    RadioButton wexinCheckBalance;
    @BindView(R.id.zfpay_check_balance)
    RadioButton zfpayCheckBalance;
    @BindView(R.id.btn_rightpay)
    Button btnRightpay;
    private int carnumber;
    private int cycle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setDefaultTitle("支付方式");
    }

    @Override
    protected void initData() {
        carnumber = getIntent().getIntExtra("carnumber", -1);
        cycle = getIntent().getIntExtra("cycle", -1);
        btnRightpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SharePreferenceHelper.getInstance(StopCarPayTypeActivity.this).getString("token", "");
                Map<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("car_id", "1");
                map.put("comm_id", "1");
                map.put("car_pay_type", cycle+"");
                map.put("plate_num", carnumber+"");
                net(false, false).post(1, Api.ParkingCharge_URL, map);
            }
        });


    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if(type==1){
            Gson gson = new Gson();
            ParkingChargeBean parkingChargeBean = gson.fromJson(data, ParkingChargeBean.class);
            ParkingChargeBean.DataBean parkingChargeBeanData = parkingChargeBean.getData();
            Toast.makeText(StopCarPayTypeActivity.this,parkingChargeBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
