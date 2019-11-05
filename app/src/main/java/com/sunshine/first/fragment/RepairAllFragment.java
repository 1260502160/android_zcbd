package com.sunshine.first.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.activity.CarInfoActivity;
import com.sunshine.first.adapter.GetCarListAdapter;
import com.sunshine.first.adapter.RepairAllAdapter;
import com.sunshine.first.bean.GetRepairListBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RepairAllFragment extends BaseFragment {
    @BindView(R.id.empty_fl)
    FrameLayout empty_fl;

    @BindView(R.id.recycle_repair_all)
    RecyclerView recycleRepairAll;
    private RepairAllAdapter repairAllAdapter;

    @Override
    protected void initData() {
        String token = SharePreferenceHelper.getInstance(getActivity()).getString("token", "");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("type", "0");
        net(false, false).post(1, Api.GetRepairList_URL, map);
    }

    @Override
    protected void initView(View view) {
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        recycleRepairAll.setLayoutManager(linearLayoutManager);
        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        repairAllAdapter = new RepairAllAdapter(getActivity());
        //设置Adapter
        recycleRepairAll.setAdapter(repairAllAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_repair_all;
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){
            Gson gson = new Gson();
            GetRepairListBean getRepairListBean = gson.fromJson(data, GetRepairListBean.class);
            List<GetRepairListBean.DataBean.ListBean> list = getRepairListBean.getData().getList();
            if (list!=null&&list.size()>0){
                repairAllAdapter.setData(list);
            }else{
                empty_fl.setVisibility(View.VISIBLE);
            }

        }
    }
}
