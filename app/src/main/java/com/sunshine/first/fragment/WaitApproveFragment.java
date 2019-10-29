package com.sunshine.first.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.adapter.HostManAdapter;
import com.sunshine.first.bean.GetResidentsListBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WaitApproveFragment extends BaseFragment {
    @BindView(R.id.recycle_fangzhu)
    RecyclerView recycleFangzhu;
    Unbinder unbinder;
    private HostManAdapter hostManAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_fangzhu_recycle;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
        //设置布局管理器
        recycleFangzhu.setLayoutManager(new LinearLayoutManager(getActivity()));

        hostManAdapter = new HostManAdapter(getActivity());
        //设置Adapter
        recycleFangzhu.setAdapter(hostManAdapter);

        int id = getActivity().getIntent().getIntExtra("id", -1);
        Map<String,String> map = new HashMap<>();
        map.put("token",getToken());
        map.put("id",id+"");
        map.put("status",2+"");
        net(false,false).post(1,Api.GetResidentsList_URL,map);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){

            GetResidentsListBean getResidentsListBean = gson.fromJson(data, GetResidentsListBean.class);
            List<GetResidentsListBean.DataBean> getResidentsListBeanData = getResidentsListBean.getData();
            hostManAdapter.setData(getResidentsListBeanData);
        }
    }
}
