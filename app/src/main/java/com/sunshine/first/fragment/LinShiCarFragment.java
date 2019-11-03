package com.sunshine.first.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.activity.StopCarPayTypeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LinShiCarFragment extends BaseFragment {
    @BindView(R.id.icon_smallerror)
    ImageView iconSmallerror;
    @BindView(R.id.text_dingdan)
    TextView textDingdan;
    @BindView(R.id.icon_mydingdan)
    TextView iconMydingdan;
    @BindView(R.id.relative_phone_number)
    RelativeLayout relativePhoneNumber;
    @BindView(R.id.text_qchc)
    TextView textQchc;
    @BindView(R.id.icon_next)
    TextView iconNext;
    @BindView(R.id.relative_safe_password)
    RelativeLayout relativeSafePassword;
    @BindView(R.id.text_stop_time)
    TextView textStopTime;
    @BindView(R.id.text_stoptime)
    TextView textStoptime;
    @BindView(R.id.relative_stop_time)
    RelativeLayout relativeStopTime;
    @BindView(R.id.text_money)
    TextView textMoney;
    @BindView(R.id.text_linmoney)
    TextView textLinmoney;
    @BindView(R.id.relative_lin_money)
    RelativeLayout relativeLinMoney;
    @BindView(R.id.btn_right_pay)
    Button btnRightPay;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_linshicar;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
        btnRightPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), StopCarPayTypeActivity.class);
                startActivity(intent);
            }
        });

    }


}
