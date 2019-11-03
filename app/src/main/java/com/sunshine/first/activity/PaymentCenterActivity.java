package com.sunshine.first.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.adapter.TextAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentCenterActivity extends BaseAppCompatActivity{
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.recycle_choose_home)
    RecyclerView recycleChooseHome;
    @BindView(R.id.recycle_pay_type)
    RecyclerView recyclePay_type;
    private Intent intent;


    @Override
    public int getLayoutId() {
        return R.layout.activity_payment_center;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //配置布局，默认为vertical（垂直布局），下边这句将布局改为水平布局
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleChooseHome.setLayoutManager(linearLayoutManager);
        recycleChooseHome.setAdapter(new TextAdapter(PaymentCenterActivity.this));
        setDefaultTitle("缴费中心");
        setRightTitle("缴费中心", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(PaymentCenterActivity.this, PayRecordActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void initData() {

    }
}
