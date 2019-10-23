package com.sunshine.first.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.sunshine.first.BaseFragment;
import com.abner.ming.base.model.Api;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.sunshine.first.R;
import com.sunshine.first.activity.RepairActivity;
import com.sunshine.first.activity.YeZhuRenZhengActivity;
import com.sunshine.first.bean.XbannerBean;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.banner)
    XBanner banner;
    Unbinder unbinder;
    private Intent intent;
    private Gson gson;
    private XbannerBean xbannerBean;
    private List<XbannerBean.DataBean> xbannerBeanData;

    @Override
    protected void initData() {

        net(false,false).get(1,Api.Xbanner_URL,null);
        iconPaymentCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RepairActivity.class);
                startActivity(intent);
            }
        });
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

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){

            gson = new Gson();
            xbannerBean = gson.fromJson(data, XbannerBean.class);
            xbannerBeanData = xbannerBean.getData();
            final ArrayList<String> images = new ArrayList<>();
            if (xbannerBeanData != null) {
                for (int i = 0; i < xbannerBeanData.size(); i++) {
                    images.add(Api.BASE_URL + xbannerBeanData.get(i).getUrl());
                }
            }
            // 为XBanner绑定数据
            banner.setData(images, null);
            // XBanner适配数据
            banner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(Serviceragment.this).load(images.get(position)).into((ImageView) view);
                }
            });
        }
    }
}
