package com.sunshine.first.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.adapter.UserManagerAdapter;
import com.sunshine.first.bean.HouseListBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择房屋
 */
public class ZhuHuGuanLiActivity extends BaseAppCompatActivity {

    @BindView(R.id.empty_fl)
    FrameLayout empty_fl;
    @BindView(R.id.btn_add_household_info)
    Button btnAddHouseholdInfo;
    @BindView(R.id.reclcle_home_list)
    RecyclerView recycleHomeList;
    private UserManagerAdapter userManagerAdapter;

    @Override
    protected void initData() {
        hashMap.clear();
        hashMap.put("token", getToken());
        hashMap.put("type", "1");
        net(true, false).post(1, Api.HousesList_URL, hashMap);
    }

    @Override
    protected void initView() {

        setDefaultTitle("选择房屋");
        userManagerAdapter = new UserManagerAdapter(this, 1);
        recycleHomeList.setLayoutManager(new LinearLayoutManager(this));
        recycleHomeList.setAdapter(userManagerAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_house;
    }


    @OnClick({R.id.btn_add_household_info})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_add_household_info:
                intent = new Intent(ZhuHuGuanLiActivity.this, HostmanRenActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (1 == type) {//获取房屋列表
            HouseListBean houseListBean = gson.fromJson(data, HouseListBean.class);
            if (houseListBean != null && houseListBean.getData() != null &&
                    houseListBean.getData().getList() != null && houseListBean.getData().getList().size() > 0) {
                userManagerAdapter.setData(houseListBean.getData().getList());
            } else {
                empty_fl.setVisibility(View.VISIBLE);
            }
        }
    }
}
