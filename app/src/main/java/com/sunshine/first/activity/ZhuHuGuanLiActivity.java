package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuHuGuanLiActivity extends AppCompatActivity {


    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.btn_add_household_info)
    Button btnAddHouseholdInfo;
    @BindView(R.id.relative_choose_home_pay)
    RelativeLayout relativeChooseHomePay;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_house);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.icon_back, R.id.btn_add_household_info,R.id.relative_choose_home_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_add_household_info:
                intent = new Intent(ZhuHuGuanLiActivity.this, AddHoueseHoldActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_choose_home_pay:
                intent = new Intent(ZhuHuGuanLiActivity.this, HouseHoldIdentity.class);
                startActivity(intent);
                break;
        }
    }


}
