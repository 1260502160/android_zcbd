package com.sunshine.first.activity;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.bean.ForgetPwdBean;
import com.sunshine.first.bean.SendSmsBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPassActivity extends BaseAppCompatActivity{
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
    private Gson gson;
    private SendSmsBean sendSmsBean;
    private String edit_code;
    private String edit_new_pass;
    private String edit_sure_newpass;
    private ForgetPwdBean forgetPwdBean;
    private ForgetPwdBean.DataBean forgetPwdBeanData;
    private CountDownTimer timer;

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


                phone = editForgetNum.getText().toString();
                edit_code = editForgetPasswords.getText().toString();
                edit_new_pass = editForgetNewpass.getText().toString();
                edit_sure_newpass = editSurenewpass.getText().toString();
                if (TextUtils.isEmpty(phone)){
                    ToastManage.s(ForgetPassActivity.this, "手机号不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edit_code)){
                    ToastManage.s(ForgetPassActivity.this, "验证码不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edit_new_pass)){
                    ToastManage.s(ForgetPassActivity.this, "新密码不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edit_sure_newpass)){
                    ToastManage.s(ForgetPassActivity.this, "确认密码不能为空！");
                    return;
                }
                Map<String,String> map = new HashMap<>();
                map.put("mobile",phone);
                map.put("code",edit_code);
                map.put("pwd",edit_new_pass);
                map.put("repwd",edit_sure_newpass);
                net(false,false).post(2,Api.RePwd_URL,map);
            }
        });


    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setDefaultTitle("忘记密码");
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_forgetpass;
    }

    @OnClick({R.id.edit_forget_num, R.id.btn_forget_yancode, R.id.edit_forget_newpass, R.id.edit_surenewpass, R.id.btn_reg_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){
            gson = new Gson();
            sendSmsBean = gson.fromJson(data, SendSmsBean.class);
            timer.start();
            Toast.makeText(ForgetPassActivity.this,sendSmsBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }

        if (type==2){
            gson = new Gson();
            forgetPwdBean = gson.fromJson(data, ForgetPwdBean.class);
            Toast.makeText(ForgetPassActivity.this,forgetPwdBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
