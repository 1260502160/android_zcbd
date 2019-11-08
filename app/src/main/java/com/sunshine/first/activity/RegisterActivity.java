package com.sunshine.first.activity;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.bean.RegisterBean;
import com.sunshine.first.bean.SendSmsBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseAppCompatActivity {

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

    private String code, phone;
    private String newpass;
    private String surepass;
    private RegisterBean registerBean;
    private CountDownTimer timer;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setDefaultTitle("注册");

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_register;
    }

    @OnClick({R.id.btn_yancode, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_yancode://获取验证码
                phone = editNum.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    ToastManage.s(this, "手机号不可为空！");
                    return;
                }
                timer = new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        btnYancode.setBackgroundColor(Color.parseColor("#FB9EA7"));
                        btnYancode.setClickable(false);
                        btnYancode.setBackgroundResource(R.drawable.shape_line);
                        btnYancode.setText("(" + millisUntilFinished / 1000 + "s)");
                    }

                    @Override
                    public void onFinish() {
                        btnYancode.setBackgroundResource(R.drawable.shape_blue);
                        btnYancode.setText("重新获取验证码");
                        btnYancode.setClickable(true);
                        btnYancode.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                };

                Map<String, String> map = new HashMap<>();
                phone = editNum.getText().toString();
                map.put("phone", phone);
                net(false, false).post(2, Api.SendSms_URL, map);
                break;
            case R.id.btn_register://注册
                phone = editNum.getText().toString();
                code = editCode.getText().toString();
                newpass = editNewpass.getText().toString();
                surepass = editSurenewpass.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    ToastManage.s(RegisterActivity.this, "手机号不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    ToastManage.s(RegisterActivity.this, "验证码不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(newpass)) {
                    ToastManage.s(RegisterActivity.this, "新密码不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(surepass)) {
                    ToastManage.s(RegisterActivity.this, "请先确认新密码！");
                    return;
                }
                if (phone != null && code != null && newpass != null && surepass != null) {

                    hashMap.clear();
                    hashMap.put("mobile", phone);
                    hashMap.put("code", code);
                    hashMap.put("pwd", newpass);
                    hashMap.put("repwd", surepass);
                    net(false, false).post(1, Api.Register_URL, hashMap);

                } else {
                    Toast.makeText(RegisterActivity.this, "输入的内容不能为空！", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            gson = new Gson();
            registerBean = gson.fromJson(data, RegisterBean.class);
            Toast.makeText(RegisterActivity.this, registerBean.getMessage().toString(), Toast.LENGTH_SHORT).show();
            if ("200".equals(registerBean.getError_code())) {
                finish();
            }

        }
        if (type == 2) {

            SendSmsBean sendSmsBean = gson.fromJson(data, SendSmsBean.class);
            Toast.makeText(RegisterActivity.this, sendSmsBean.getMessage().toString(), Toast.LENGTH_SHORT).show();
            timer.start();
        }
    }
}
