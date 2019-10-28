package com.sunshine.first.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.ShowOwnerVerifyBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdentityAdfitActivity extends BaseAppCompatActivity {

    private int id;
    private String token;
    @Override
    protected void initData() {
       token =  SharePreferenceHelper.getInstance(IdentityAdfitActivity.this).getString("token", "");
        // TODO: 2019/10/28  id是上一级列表页传过来的
    }

    @Override
    protected void initView() {


    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_identity_audit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_no_pass, R.id.tv_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_no_pass:
                ownerCheck(2);
                break;
            case R.id.tv_pass:
                ownerCheck(1);
                break;
        }
    }

    private void ownerCheck(int status) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("id", id+"");
        map.put("status", status+"");
        net(false, false).post(1, Api.Owner_Check, map);
    }



    @Override
    public void success(int type, String data) {
        super.success(type, data);

        if (type == 1) {

            Gson gson = new Gson();
            ShowOwnerVerifyBean showOwnerVerifyBean = gson.fromJson(data, ShowOwnerVerifyBean.class);
            Toast.makeText(IdentityAdfitActivity.this, showOwnerVerifyBean.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
