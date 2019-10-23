package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.abner.ming.base.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.adapter.GetCarListAdapter;
import com.sunshine.first.adapter.UserManagerAdapter;
import com.sunshine.first.bean.HouseListBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserMangerActivity extends BaseAppCompatActivity {

    @BindView(R.id.recycle_usemanger)
    RecyclerView recycleUsemanger;
    @BindView(R.id.btn_add_infomation)
    Button btnAddInfomation;
    private UserManagerAdapter userManagerAdapter;
    private List<HouseListBean.DataBean> houseListBeanData;

    @Override
    protected void initData() {

        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserMangerActivity.this);
        //设置布局管理器
        recycleUsemanger.setLayoutManager(linearLayoutManager);
        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        userManagerAdapter = new UserManagerAdapter(UserMangerActivity.this);

        //设置Adapter
        recycleUsemanger.setAdapter(userManagerAdapter);

        String token = SharePreferenceHelper.getInstance(UserMangerActivity.this).getString("token", "");
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("type","1");
        net(false, false).post(1, Api.HousesList_URL, map);

        btnAddInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMangerActivity.this, FamilyIdentityActivity.class);
                startActivity(intent);
            }
        });
        setDefaultTitle("房屋列表");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_usermanger;
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){

            Gson gson = new Gson();
            HouseListBean houseListBean = gson.fromJson(data, HouseListBean.class);
            houseListBeanData = houseListBean.getData();
            if (houseListBean!=null){
                userManagerAdapter.setData(houseListBeanData);
            }
        }
    }
}
