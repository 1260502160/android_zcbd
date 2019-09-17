package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneNumeberActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.edit_new_phone)
    EditText editNewPhone;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.btn_huo_code)
    Button btnHuoCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_changephone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.edit_new_phone, R.id.edit_code, R.id.btn_huo_code, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.edit_new_phone:
                break;
            case R.id.edit_code:
                break;
            case R.id.btn_huo_code:
                break;
            case R.id.btn_submit:
                break;
        }
    }
}
