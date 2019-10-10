package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.abner.ming.base.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.bean.RegisterBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseAppCompatActivity{
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.edit_num)
    EditText editNum;
    @BindView(R.id.btn_yancode)
    EditText btnYancode;
    @BindView(R.id.edit_newpass)
    EditText editNewpass;
    @BindView(R.id.edit_surenewpass)
    EditText editSurenewpass;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private String code,phone;
    private String newpass;
    private String surepass;
    private Gson gson;
    private RegisterBean registerBean;
    private Button btn_register;


    @Override
    protected void initData() {

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phone = editNum.getText().toString();
                code = btnYancode.getText().toString();
                newpass = editNewpass.getText().toString();
                surepass = editSurenewpass.getText().toString();
                Map<String,String> map = new HashMap<>();
                map.put("mobile",phone);
                map.put("code",code);
                map.put("pwd",newpass);
                map.put("repwd",surepass);
                net(false,false).post(1,Api.Register_URL,map);

            }
        });



    }

    @Override
    protected void initView() {

        btn_register = (Button) get(R.id.btn_register);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_register;
    }

    @OnClick({R.id.icon_back, R.id.edit_num, R.id.btn_yancode, R.id.edit_newpass, R.id.edit_surenewpass, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.edit_num:
                break;
            case R.id.btn_yancode:
                break;
            case R.id.edit_newpass:
                break;
            case R.id.edit_surenewpass:
                break;
            case R.id.btn_register:
                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){
            gson = new Gson();
            registerBean = gson.fromJson(data, RegisterBean.class);
            if (registerBean.isSuccess()){
                Toast.makeText(RegisterActivity.this,registerBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(RegisterActivity.this,registerBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }

        }
    }
}
