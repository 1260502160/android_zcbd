package com.sunshine.first.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunshine.first.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.MainActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.LoginBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseAppCompatActivity {
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.text_forgetpass)
    TextView textForgetpass;
    @BindView(R.id.text_register)
    TextView textRegister;
    private Intent intent;
    private String editphone;
    private String editpassword;
    private HashMap<String, String> map;
    private Button btn_login;
    private String phone;
    private String pwd;
    private Gson gson;
    private LoginBean loginBean;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        btn_login = (Button) get(R.id.btn_login);

        if (!TextUtils.isEmpty(getToken())) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void initData() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = editPhone.getText().toString();
                pwd = editPassword.getText().toString();
                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
                    Map<String, String> map = new HashMap<>();
                    map.put("mobile", phone);
                    map.put("pwd", pwd);
                    net(false, false).post(1, Api.Login_URL, map);
                    Log.i("ssss", "net");

                } else {
                    Toast.makeText(LoginActivity.this, "手机号或密码不能为空！", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            //Log.i("aaa","++++");
            gson = new Gson();
            loginBean = gson.fromJson(data, LoginBean.class);
            Log.i("aaa", "++++");

            if (loginBean.isSuccess()) {
                SharePreferenceHelper.getInstance(LoginActivity.this).put("token", loginBean.getData().getToken());
                SharePreferenceHelper.getInstance(LoginActivity.this).put("is_verify", loginBean.getData().getIs_verify());
                SharePreferenceHelper.getInstance(LoginActivity.this).put("phone", phone);
                intent = new Intent(LoginActivity.this, MainActivity.class);
                //intent.putExtra("phone",phone);
                startActivity(intent);
                finish();
                // Toast.makeText(LoginActivity.this,loginBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
            } else {
                Log.i("aaa", "++++");
                Toast.makeText(LoginActivity.this, loginBean.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.login_layout;
    }


    @OnClick({R.id.edit_phone, R.id.edit_password, R.id.btn_login, R.id.text_forgetpass, R.id.text_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_phone:
                break;
            case R.id.edit_password:
                break;
            case R.id.btn_login:
                break;
            case R.id.text_forgetpass:
                intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
                startActivity(intent);
                break;
            case R.id.text_register:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

}
