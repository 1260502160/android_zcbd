package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SafeMangerActivity extends BaseAppCompatActivity{

    @BindView(R.id.relative_phone_number)
    RelativeLayout relativephonenumber;
    @BindView(R.id.relative_safe_password)
    RelativeLayout relativeSafePassword;
    private Intent intent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_safe_manger;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setDefaultTitle("安全");
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.relative_phone_number, R.id.relative_safe_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.relative_phone_number:
                intent = new Intent(SafeMangerActivity.this, PhoneNumeberActivity.class);
                startActivity(intent);
                break;
            case R.id.relative_safe_password:
                intent = new Intent(SafeMangerActivity.this, UpdatePassActivity.class);
                startActivity(intent);
                break;
        }
    }
}
