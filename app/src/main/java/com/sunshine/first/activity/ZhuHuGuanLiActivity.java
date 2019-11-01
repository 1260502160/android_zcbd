package com.sunshine.first.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择房屋
 */
public class ZhuHuGuanLiActivity extends BaseAppCompatActivity {


    @BindView(R.id.btn_add_household_info)
    Button btnAddHouseholdInfo;
    @BindView(R.id.reclcle_home_list)
    RecyclerView recycleHomeList;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        setDefaultTitle("选择房屋");
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


}
