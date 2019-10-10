package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPassActivity extends AppCompatActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.edit_forget_num)
    EditText editForgetNum;
    @BindView(R.id.btn_forget_yancode)
    Button btnForgetYancode;
    @BindView(R.id.edit_forget_newpass)
    EditText editForgetNewpass;
    @BindView(R.id.edit_surenewpass)
    EditText editSurenewpass;
    @BindView(R.id.btn_reg_sure)
    Button btnRegSure;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forgetpass);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.edit_forget_num, R.id.btn_forget_yancode, R.id.edit_forget_newpass, R.id.edit_surenewpass, R.id.btn_reg_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.edit_forget_num:
                break;
            case R.id.btn_forget_yancode:
                break;
            case R.id.edit_forget_newpass:
                break;
            case R.id.edit_surenewpass:
                break;
            case R.id.btn_reg_sure:
                break;
        }
    }
}
