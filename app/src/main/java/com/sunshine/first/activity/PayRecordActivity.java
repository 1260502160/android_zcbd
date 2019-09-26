package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayRecordActivity extends AppCompatActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.icon_down)
    ImageView iconDown;
    @BindView(R.id.relative_info)
    RelativeLayout relativeInfo;
    @BindView(R.id.icon_up)
    ImageView iconUp;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_record);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.icon_down, R.id.icon_up,R.id.relative_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.relative_info:
                intent = new Intent(PayRecordActivity.this, PayDeatilActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_down:
                iconUp.setVisibility(View.VISIBLE);
                iconDown.setVisibility(View.GONE);
                relativeInfo.setVisibility(View.VISIBLE);
                break;
            case R.id.icon_up:
                iconDown.setVisibility(View.VISIBLE);
                iconUp.setVisibility(View.GONE);
                relativeInfo.setVisibility(View.GONE);
                break;
        }
    }
}
