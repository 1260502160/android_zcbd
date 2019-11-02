package com.sunshine.first.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 更换手机号
 */
public class PhoneNumeberActivity extends BaseAppCompatActivity {

    @BindView(R.id.edit_new_phone)
    EditText editNewPhone;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.btn_huo_code)
    Button btnHuoCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private String phone;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);
        setDefaultTitle("更换手机号");
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_changephone;
    }

    @OnClick({R.id.edit_new_phone, R.id.edit_code, R.id.btn_huo_code, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_new_phone:
                break;
            case R.id.edit_code:
                break;
            case R.id.btn_huo_code://获取验证码
                phone = editNewPhone.getText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    Map<String, String> map = new HashMap<>();
                    map.put("phone", phone);
                    net(false, false).post(1, Api.SendSms_URL, map);
                } else {
                    ToastManage.s(this, "请输入新手机号!");
                }
                break;
            case R.id.btn_submit://更换手机号
                String code = editCode.getText().toString();
                if (!TextUtils.isEmpty(phone)) {

                    Map<String, String> map = new HashMap<>();
                    map.put("token", getToken());
                    map.put("mobile", phone);
                    map.put("code", code);
                    map.put("type", "2");
                    net(false, false).post(1, Api.UPDATE_MOBILE_URL, map);
                } else {
                    ToastManage.s(this, "请输入验证码！");
                }
                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);

        if (type == 1) {
//            SendSmsBean sendSmsBean = gson.fromJson(data, SendSmsBean.class);
//            if (sendSmsBean != null && sendSmsBean.isSuccess()) {
//
//            }

        }

    }
}
