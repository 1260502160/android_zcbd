package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaoXiuTiShiActivity extends AppCompatActivity {

    @BindView(R.id.btn_lookdeail)
    Button btnLookdeail;
    @BindView(R.id.btn_homeback)
    Button btnHomeback;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_xiu_ti_shi);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_lookdeail, R.id.btn_homeback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_lookdeail:
                intent = new Intent(BaoXiuTiShiActivity.this, BaoLookDeatilActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_homeback:
                break;
        }
    }
}
