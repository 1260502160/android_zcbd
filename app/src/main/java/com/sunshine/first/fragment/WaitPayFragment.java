package com.sunshine.first.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.adapter.RepairAgencyAdapter;
import com.sunshine.first.adapter.RepairPayAdapter;
import com.sunshine.first.bean.GetRepairListBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class WaitPayFragment extends BaseFragment{
    @BindView(R.id.recycle_repair_waitpay)
    RecyclerView recycleRepairWaitpay;
    private RepairAgencyAdapter repairAgencyAdapter;
    private RepairPayAdapter repairPayAdapter;

    @Override
    protected void initData() {

        String token = SharePreferenceHelper.getInstance(getActivity()).getString("token", "");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("type", "2");
        net(false, false).post(1, Api.GetRepairList_URL, map);
    }

    @Override
    protected void initView(View view) {
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        recycleRepairWaitpay.setLayoutManager(linearLayoutManager);
        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        repairPayAdapter = new RepairPayAdapter(getActivity());
        //设置Adapter
        recycleRepairWaitpay.setAdapter(repairAgencyAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_repair_waitpay;
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){
            Gson gson = new Gson();
            GetRepairListBean getRepairListBean = gson.fromJson(data, GetRepairListBean.class);
            List<GetRepairListBean.DataBean.ListBean> list = getRepairListBean.getData().getList();
            if (list!=null){
                repairPayAdapter.setData(list);
            }

        }
    }
}
