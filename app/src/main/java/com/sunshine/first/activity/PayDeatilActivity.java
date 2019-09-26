package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayDeatilActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_deatil);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.icon_back)
    public void onViewClicked() {
        finish();
    }
}
