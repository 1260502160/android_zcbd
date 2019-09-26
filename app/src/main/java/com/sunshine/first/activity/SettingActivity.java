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

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.relative_setting_safe)
    RelativeLayout relativeSettingSafe;
    @BindView(R.id.relative_setting_qchc)
    RelativeLayout relativeSettingQchc;
    @BindView(R.id.relative_setting_banbengengxin)
    RelativeLayout relativeSettingBanbengengxin;
    @BindView(R.id.relative_setting_aboutus)
    RelativeLayout relativeSettingAboutus;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.relative_setting_safe, R.id.relative_setting_qchc, R.id.relative_setting_banbengengxin, R.id.relative_setting_aboutus, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.relative_setting_safe:
                intent = new Intent(SettingActivity.this, SafeMangerActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_setting_qchc:
                break;
            case R.id.relative_setting_banbengengxin:
                break;
            case R.id.relative_setting_aboutus:
                break;
        }
    }
}
