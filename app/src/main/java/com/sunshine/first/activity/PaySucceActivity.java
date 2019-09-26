package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaySucceActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.btn_lookdeail)
    Button btnLookdeail;
    @BindView(R.id.btn_homeback)
    Button btnHomeback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_succe);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.btn_lookdeail, R.id.btn_homeback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_lookdeail:
                break;
            case R.id.btn_homeback:
                finish();
                break;
        }
    }
}
