package com.sunshine.first.activity;

import com.sunshine.first.BaseAppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.adapter.FangKeAdapter;
import com.sunshine.first.adapter.GetCarListAdapter;
import com.sunshine.first.bean.GetResidentsListBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FangkeActivity extends BaseAppCompatActivity{

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.text_forget)
    TextView textForget;
    @BindView(R.id.relative_change)
    RelativeLayout relativeChange;
    @BindView(R.id.view_fangzhurenzheng_one)
    View viewFangzhurenzhengOne;
    @BindView(R.id.recycle_fangke)
    RecyclerView recycleFangke;
    private GetCarListAdapter getCarListAdapter;
    private FangKeAdapter fangKeAdapter;

    @Override
    protected void initData() {
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FangkeActivity.this);
        //设置布局管理器
        recycleFangke.setLayoutManager(linearLayoutManager);
        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        fangKeAdapter = new FangKeAdapter(FangkeActivity.this);

        //设置Adapter
        recycleFangke.setAdapter(getCarListAdapter);

        String token = SharePreferenceHelper.getInstance(this).getString("token", "");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("id", "1");
        map.put("status", "1");
        net(false, false).post(1, Api.GetResidentsList_URL, map);


    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sfrz;
    }



    @OnClick({R.id.icon_back, R.id.text_forget, R.id.relative_change, R.id.view_fangzhurenzheng_one, R.id.recycle_fangke})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.text_forget:
                break;
            case R.id.relative_change:
                break;
            case R.id.view_fangzhurenzheng_one:
                break;
            case R.id.recycle_fangke:
                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){
            Gson gson = new Gson();
            //GetResidentsListBean getResidentsListBean = gson.fromJson(data, GetResidentsListBean.class);
        }
    }
}
