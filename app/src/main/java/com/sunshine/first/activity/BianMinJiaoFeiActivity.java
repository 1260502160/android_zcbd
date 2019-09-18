package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BianMinJiaoFeiActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.btn_stopcar)
    Button btnStopcar;
    @BindView(R.id.btn_wuyefei)
    Button btnWuyefei;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bian_min_jiao_fei);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.btn_stopcar, R.id.btn_wuyefei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_stopcar:
                intent = new Intent(BianMinJiaoFeiActivity.this, StopCarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_wuyefei:
                break;
        }
    }
}
