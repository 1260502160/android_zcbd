package com.sunshine.first.activity;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sunshine.first.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.bean.RegisterBean;
import com.sunshine.first.bean.SendSmsBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseAppCompatActivity{
    @BindView(R.id.edit_num)
    EditText editNum;
    @BindView(R.id.btn_yancode)
    Button btnYancode;
    @BindView(R.id.edit_newpass)
    EditText editNewpass;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.edit_surenewpass)
    EditText editSurenewpass;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private String code,phone;
    private String newpass;
    private String surepass;
    private RegisterBean registerBean;
    private CountDownTimer timer;
    private Button btn_register;


    @Override
    protected void initData() {

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = editNum.getText().toString();
                code = editCode.getText().toString();
                newpass = editNewpass.getText().toString();
                surepass = editSurenewpass.getText().toString();
                if (phone!=null&&code!=null&&newpass!=null&&surepass!=null){

                    Map<String,String> map = new HashMap<>();
                    map.put("mobile",phone);
                    map.put("code",code);
                    map.put("pwd",newpass);
                    map.put("repwd",surepass);
                    net(false,false).post(1,Api.Register_URL,map);

                }else {
                    Toast.makeText(RegisterActivity.this,"输入的内容不能为空！",Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnYancode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer  = new CountDownTimer(60000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        btnYancode.setBackgroundColor(Color.parseColor("#FB9EA7"));
                        btnYancode.setClickable(false);
                        btnYancode.setBackgroundResource(R.drawable.shape_line);
                        btnYancode.setText("("+millisUntilFinished / 1000 +"s)");
                    }

                    @Override
                    public void onFinish() {
                        btnYancode.setText("重新获取验证码");
                        btnYancode.setClickable(true);
                        btnYancode.setBackgroundColor(Color.parseColor("#FB9EA7"));
                    }
                };

                Map<String,String> map = new HashMap<>();
                phone = editNum.getText().toString();
                map.put("phone",phone);
                net(false,false).post(2,Api.SendSms_URL,map);


            }
        });

    }

    @Override
    protected void initView() {

        btn_register = (Button) get(R.id.btn_register);
       setDefaultTitle("注册");
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_register;
    }

    @OnClick({R.id.edit_num, R.id.btn_yancode, R.id.edit_newpass, R.id.edit_surenewpass, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
        if (type==2){

            SendSmsBean sendSmsBean = gson.fromJson(data, SendSmsBean.class);
            Toast.makeText(RegisterActivity.this,sendSmsBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
            timer.start();
        }
    }
}
