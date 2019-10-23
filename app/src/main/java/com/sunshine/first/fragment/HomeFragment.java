package com.sunshine.first.fragment;


import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunshine.first.BaseFragment;
import com.abner.ming.base.model.Api;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.sunshine.first.R;
import com.sunshine.first.activity.OnlineStoreActivity;
import com.sunshine.first.activity.VisitorRegistrationActivity;
import com.sunshine.first.activity.YeZhuRenZhengActivity;
import com.sunshine.first.adapter.StoreAllAdapter;
import com.sunshine.first.bean.GoodsListBean;
import com.sunshine.first.bean.XbannerBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Author：wtt
 * @E-mail： 15001194794@163.com
 * @Date：2019/3/5 19:27
 * @Description：描述信息
 */
public class HomeFragment extends BaseFragment{

    public static final String AAAA = "aaaa";
    @BindView(R.id.banner)
    XBanner banner;
   /* @BindView(R.id.icon_visitor_registration)
    ImageView iconVisitorRegistration;*/
    @BindView(R.id.icon_store)
    ImageView iconStore;
    @BindView(R.id.icon_payment_center)
    ImageView iconPaymentCenter;
    @BindView(R.id.icon_online_repair)
    ImageView iconOnlineRepair;
    @BindView(R.id.tv_hot_store)
    TextView tvHotStore;
    @BindView(R.id.home_recycle)
    RecyclerView homeRecycle;
    @BindView(R.id.icon_owner_certification)
    ImageView iconOwnerCertification;

    Unbinder unbinder;
   /* *//* @BindView(R.id.icon_yezhurenzheng)
        ImageView iconYezhurenzheng;
        @BindView(R.id.icon_shequstore)
        ImageView iconShequstore;
        @BindView(R.id.icon_fangkedengji)
        ImageView iconFangkedengji;
        @BindView(R.id.icon_baoxiu)
        ImageView iconBaoxiu;
        @BindView(R.id.icon_bianminjiaofei)
        ImageView iconBianminjiaofei;
        Unbinder unbinder;
        private View view;
        private Intent intent;*/
    private View view;
    private Intent intent;
    private Gson gson;
    private XbannerBean xbannerBean;
    private List<XbannerBean.DataBean> xbannerBeanData;
    private GoodsListBean goodsListBean;
    private StoreAllAdapter storeAllAdapter;
    private GoodsListBean.DataBean goodsListBeanData;
    private String token;
   // private int verity;


    @Override
    protected void initData() {

        net(false,false).get(1,Api.Xbanner_URL,null);
        int verify = SharePreferenceHelper.getInstance(getContext()).getInt("is_verify",-1);
        Log.d("verify", "onViewClicked: " +verify);
        if (verify == 0){
            iconOwnerCertification.setVisibility(View.VISIBLE);
            iconOwnerCertification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(getContext(), YeZhuRenZhengActivity.class);
                    startActivity(intent);
                }
            });

        }else if (verify== 1){
            iconOwnerCertification.setVisibility(View.GONE);
        }else if (verify==2){
            // verity=2;
        }else if (verify==3){
            //verity=3;
        }

        //创建布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        //设置布局管理器
        homeRecycle.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        storeAllAdapter = new StoreAllAdapter(getActivity());
        //设置Adapter
        homeRecycle.setAdapter(storeAllAdapter);
        token = SharePreferenceHelper.getInstance(getContext()).getString("token", "");
        Log.i("token",token.toString());
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        net(false,false).post(2,Api.GoodsList_URL,map);


    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @OnClick({R.id.banner, R.id.icon_visitor_registration, R.id.icon_store, R.id.icon_payment_center, R.id.icon_online_repair, R.id.tv_hot_store, R.id.home_recycle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.banner:
                break;
            case R.id.icon_visitor_registration:
                /*intent = new Intent(getContext(), VisitorRegistrationActivity.class);
                startActivity(intent);*/
                break;
            case R.id.icon_store:
              /*  intent = new Intent(getContext(), OnlineStoreActivity.class);
                startActivity(intent);*/
                break;
            case R.id.icon_payment_center:
                intent = new Intent(getContext(), VisitorRegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_online_repair:
                intent = new Intent(getContext(), OnlineStoreActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_hot_store:
                break;
            case R.id.home_recycle:
                break;
           case R.id.icon_owner_certification:
               break;
        }
    }
/*
    @OnClick({R.id.icon_yezhurenzheng, R.id.icon_shequstore, R.id.icon_fangkedengji, R.id.icon_baoxiu, R.id.icon_bianminjiaofei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_yezhurenzheng:
                intent = new Intent(getContext(), YeZhuRenZhengActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_shequstore:
              *//*  intent = new Intent(getContext(), FamilyIdentityActivity.class);
                startActivity(intent);*//*
                break;
            case R.id.icon_fangkedengji:
                intent = new Intent(getContext(), FangKeDengJiActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_baoxiu:
                intent = new Intent(getContext(), BaoXiuActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_bianminjiaofei:
                intent = new Intent(getContext(), BianMinJiaoFeiActivity.class);
                startActivity(intent);
                break;
        }
    }*/

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
                    Glide.with(HomeFragment.this).load(images.get(position)).into((ImageView) view);
                }
            });

        }
        if (type==2){
            gson = new Gson();
            Log.i(AAAA,data.toString());
            goodsListBean = gson.fromJson(data, GoodsListBean.class);
            goodsListBeanData = goodsListBean.getData();
            List<GoodsListBean.DataBean.ListBean> listbean = goodsListBeanData.getList();
            Log.i(AAAA,listbean.toString());
            if (listbean!=null){
                storeAllAdapter.setDataList(listbean);
            }

        }
    }
}
