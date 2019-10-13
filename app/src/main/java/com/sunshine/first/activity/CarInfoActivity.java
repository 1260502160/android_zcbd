package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.abner.ming.base.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.adapter.GetCarListAdapter;
import com.sunshine.first.adapter.StoreAllAdapter;
import com.sunshine.first.bean.GetCarListBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarInfoActivity extends BaseAppCompatActivity{
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.recycle_carinfo)
    RecyclerView recycleCarinfo;
    @BindView(R.id.btn_add_car_info)
    Button btnaAddCarInfo;
    private Intent intent;
    private String token;
    private Gson gson;
    private GetCarListBean getCarListBean;
    private LinearLayoutManager linearLayoutManager;
    private GetCarListAdapter getCarListAdapter;
    private List<GetCarListBean.DataBean> carListBeanData;


    @Override
    protected void initData() {

        //创建布局管理器
        linearLayoutManager = new LinearLayoutManager(CarInfoActivity.this);
        //设置布局管理器
        recycleCarinfo.setLayoutManager(linearLayoutManager);
        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        getCarListAdapter = new GetCarListAdapter(CarInfoActivity.this);
        //设置Adapter
        recycleCarinfo.setAdapter(getCarListAdapter);

        token = SharePreferenceHelper.getInstance(CarInfoActivity.this).getString("token", "");
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        net(false,false).post(1,Api.GetCarList_URL,map);


    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_car_list;
    }

    @OnClick({R.id.icon_back, R.id.btn_add_car_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_add_car_info:
                intent = new Intent(CarInfoActivity.this, ChooseHomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if(type==1){
            gson = new Gson();
            getCarListBean = gson.fromJson(data, GetCarListBean.class);
            carListBeanData = getCarListBean.getData();
            if (carListBeanData!=null){
                getCarListAdapter.setDataList(carListBeanData);
            }
        }
    }
}
