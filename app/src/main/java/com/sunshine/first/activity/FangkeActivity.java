package com.sunshine.first.activity;

import com.sunshine.first.BaseAppCompatActivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.adapter.FangKeAdapter;
import com.sunshine.first.bean.GetResidentsListBean;

import java.util.List;

import butterknife.BindView;

/**
 * 房客房屋列表展示
 */
public class FangkeActivity extends BaseAppCompatActivity {

    @BindView(R.id.recycle_fangke)
    RecyclerView recycleFangke;
    @BindView(R.id.btn_add_home_info)
    Button btnAddHomeInfo;


    private FangKeAdapter fangKeAdapter;

    @Override
    protected void initData() {
        //设置布局管理器
        recycleFangke.setLayoutManager(new LinearLayoutManager(this));
        fangKeAdapter = new FangKeAdapter(FangkeActivity.this);
        //设置Adapter
        recycleFangke.setAdapter(fangKeAdapter);

        hashMap.clear();
        hashMap.put("token", getToken());
        hashMap.put("id", String.valueOf(getIntent().getIntExtra("id", -1)));
        hashMap.put("status", "1");
        net(false, false).post(1, Api.GetResidentsList_URL, hashMap);

        btnAddHomeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FangkeActivity.this, FamilyIdentityActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initView() {
        setDefaultTitle("住户认证");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sfrz;
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            Gson gson = new Gson();
            GetResidentsListBean getResidentsListBean = gson.fromJson(data, GetResidentsListBean.class);
            if (getResidentsListBean != null) {
                List<GetResidentsListBean.DataBean> getResidentsListBeanData = getResidentsListBean.getData();
                fangKeAdapter.setData(getResidentsListBeanData);
            }
        }
    }
}
