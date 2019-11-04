package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.utils.SharePreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *设置
 */
public class SettingActivity extends BaseAppCompatActivity {

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setDefaultTitle("设置");
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.relative_setting_safe, R.id.relative_setting_qchc, R.id.relative_setting_banbengengxin, R.id.relative_setting_aboutus, R.id.btn_back})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_back:
                //重新登录
                SharePreferenceHelper.getInstance(this).put("token", "");
                intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.relative_setting_safe:
                intent = new Intent(SettingActivity.this, SafeMangerActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_setting_qchc:
                intent = new Intent(SettingActivity.this, UpdatePassActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_setting_banbengengxin:
                break;
            case R.id.relative_setting_aboutus:
                Intent intent1 = new Intent(SettingActivity.this, AboutUsActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
