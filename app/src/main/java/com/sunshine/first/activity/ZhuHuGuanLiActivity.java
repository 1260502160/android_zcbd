package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.abner.ming.base.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuHuGuanLiActivity extends BaseAppCompatActivity{


    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.btn_add_household_info)
    Button btnAddHouseholdInfo;
    @BindView(R.id.recycle_home_list)
    RecyclerView recycleHomeList;
    private Intent intent;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_house;
    }


    @OnClick({R.id.icon_back, R.id.btn_add_household_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_add_household_info:
                intent = new Intent(ZhuHuGuanLiActivity.this, AddHoueseHoldActivity.class);
                startActivity(intent);
                break;
         /*   case R.id.relative_choose_home_pay:
                intent = new Intent(ZhuHuGuanLiActivity.this, HouseHoldIdentity.class);
                startActivity(intent);
                break;*/
        }
    }


}
