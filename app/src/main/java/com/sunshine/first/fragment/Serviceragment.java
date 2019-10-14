package com.sunshine.first.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abner.ming.base.BaseFragment;
import com.stx.xhb.xbanner.XBanner;
import com.sunshine.first.R;
import com.sunshine.first.activity.YeZhuRenZhengActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Serviceragment extends BaseFragment {
    @BindView(R.id.icon_visitor_registration)
    ImageView iconVisitorRegistration;
    @BindView(R.id.icon_store)
    ImageView iconStore;
    @BindView(R.id.icon_payment_center)
    ImageView iconPaymentCenter;
    @BindView(R.id.icon_owner_certification)
    ImageView iconOwnerCertification;
    @BindView(R.id.tv_hot_store)
    TextView tvHotStore;
    Unbinder unbinder;
    private Intent intent;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        unbinder = ButterKnife.bind(this, view);
        iconOwnerCertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), YeZhuRenZhengActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_service;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
