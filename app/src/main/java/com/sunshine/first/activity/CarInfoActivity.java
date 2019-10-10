package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarInfoActivity extends AppCompatActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.btn_add_home_authen)
    Button btnAddHomeAuthen;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_car_list);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.btn_add_home_authen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_add_home_authen:
                intent = new Intent(CarInfoActivity.this, ChooseHomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
