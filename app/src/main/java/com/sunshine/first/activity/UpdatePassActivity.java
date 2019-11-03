package com.sunshine.first.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.ForgetPwdBean;
import com.sunshine.first.bean.SendSmsBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePassActivity extends BaseAppCompatActivity{

    @BindView(R.id.edit_forget_num)
    EditText editForgetNum;
    @BindView(R.id.btn_forget_yancode)
    Button btnForgetYancode;
    @BindView(R.id.edit_forget_newpass)
    EditText editForgetNewpass;
    @BindView(R.id.edit_forget_passwords)
    EditText editForgetPasswords;
    @BindView(R.id.edit_surenewpass)
    EditText editSurenewpass;
    @BindView(R.id.btn_reg_sure)
    Button btnRegSure;
    private String phone;
    private SendSmsBean sendSmsBean;
    private String edit_code;
    private String edit_new_pass;
    private String edit_sure_newpass;
    private ForgetPwdBean forgetPwdBean;
    private ForgetPwdBean.DataBean forgetPwdBeanData;
    private CountDownTimer timer;
    @Override
    public int getLayoutId() {
        return R.layout.activity_update_pass;
    }

    @Override
    protected void initView() {

        setDefaultTitle("修改密码");
    }

    @Override
    protected void initData() {

        btnForgetYancode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timer=new CountDownTimer(60000,1000){

                    @Override
                    public void onTick(long l) {
                        btnForgetYancode.setBackgroundColor(Color.parseColor("#FB9EA7"));
                        btnForgetYancode.setClickable(false);
                        btnForgetYancode.setBackgroundResource(R.drawable.shape_blue);
                        btnForgetYancode.setText("("+l/1000+"s)");

                    }

                    @Override
                    public void onFinish() {

                        btnForgetYancode.setClickable(true);
                        btnForgetYancode.setText("重新获取验证码");
                        btnForgetYancode.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                };
                Map<String,String> map = new HashMap<>();
                phone = editForgetNum.getText().toString();
                map.put("phone",phone);
                net(false,false).post(1,Api.SendSms_URL,map);
            }
        });


        btnRegSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> map = new HashMap<>();
                phone = editForgetNum.getText().toString();
                edit_code = editForgetPasswords.getText().toString();
                edit_new_pass = editForgetNewpass.getText().toString();
                edit_sure_newpass = editSurenewpass.getText().toString();
                map.put("mobile",phone);
                map.put("code",edit_code);
                map.put("pwd",edit_new_pass);
                map.put("repwd",edit_sure_newpass);
                net(false,false).post(2,Api.RePwd_URL,map);
            }
        });


    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){
            sendSmsBean = gson.fromJson(data, SendSmsBean.class);
            timer.start();
            Toast.makeText(UpdatePassActivity.this,sendSmsBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
        if (type==2){
            forgetPwdBean = gson.fromJson(data, ForgetPwdBean.class);
            Toast.makeText(UpdatePassActivity.this,forgetPwdBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
