package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.adapter.GrideAdapter;
import com.sunshine.first.adapter.TextAdapter;
import com.sunshine.first.bean.GrideBean;
import com.sunshine.first.bean.HouseListBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BianMinJiaoFeiActivity extends BaseAppCompatActivity {

    @BindView(R.id.recycle_choose_home)
    RecyclerView recycleChooseHome;
    @BindView(R.id.recycle_pay_type)
    RecyclerView recyclePayType;
    private Intent intent;
    private String token;
    private HouseListBean houseListBean;
    private TextAdapter textAdapter;
    private GridLayoutManager gridLayoutManager;
    private GrideAdapter grideAdapter;
    private int community_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bian_min_jiao_fei;
    }

    @Override
    protected void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //配置布局，默认为vertical（垂直布局），下边这句将布局改为水平布局
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleChooseHome.setLayoutManager(linearLayoutManager);
        textAdapter = new TextAdapter(BianMinJiaoFeiActivity.this);
        recycleChooseHome.setAdapter(textAdapter);
        setDefaultTitle("缴费中心");
        setRightTitle("缴费记录", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(BianMinJiaoFeiActivity.this, PayRecordActivity.class);

                startActivity(intent);
            }
        });

        gridLayoutManager = new GridLayoutManager(BianMinJiaoFeiActivity.this, 3);
        recyclePayType.setLayoutManager(gridLayoutManager);
        List<GrideBean> list = new ArrayList<>();
        list.add(new GrideBean(R.mipmap.water,"水费"));
        list.add(new GrideBean(R.mipmap.dianfei,"电费"));
        list.add(new GrideBean(R.mipmap.yanqifei,"燃气费"));
        list.add(new GrideBean(R.mipmap.guhua,"固话"));
        list.add(new GrideBean(R.mipmap.kuandai,"宽带"));
        list.add(new GrideBean(R.mipmap.onlinedianshi,"有线电视"));
        list.add(new GrideBean(R.mipmap.tingchefei,"停车费"));
        list.add(new GrideBean(R.mipmap.wuyefei,"物业费"));
        list.add(new GrideBean(R.mipmap.nuanqifei,"暖气费"));

        grideAdapter = new GrideAdapter(BianMinJiaoFeiActivity.this, list);
        recyclePayType.setAdapter(grideAdapter);

    }

    @Override
    protected void initData() {

        token = SharePreferenceHelper.getInstance(BianMinJiaoFeiActivity.this).getString("token", "");
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("type", "1");
        net(false, false).post(1, Api.HousesList_URL, map);
    }



    /*@BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.btn_stopcar)
    Button btnStopcar;
    @BindView(R.id.btn_wuyefei)
    Button btnWuyefei;
    private Intent intent;*/
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bian_min_jiao_fei);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.btn_stopcar, R.id.btn_wuyefei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_stopcar:
                intent = new Intent(BianMinJiaoFeiActivity.this, StopCarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_wuyefei:
               intent = new Intent(BianMinJiaoFeiActivity.this, PropertyChargesActivity.class);
                startActivity(intent);
                break;
        }
    }*/


    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {

            houseListBean = gson.fromJson(data, HouseListBean.class);
            if (houseListBean.getData() != null && houseListBean.getData().getList() != null && houseListBean.getData().getList().size() > 0) {
                textAdapter.setData(houseListBean.getData().getList());
                community_id = houseListBean.getData().getList().get(0).getCommunity_id();
                if (grideAdapter!=null){
                    grideAdapter.setData(community_id);
                }


            }
        }
    }
}
