package com.sunshine.first.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.adapter.RepairPayAdapter;
import com.sunshine.first.bean.GetRepairListBean;

import java.util.List;

import butterknife.BindView;

public class WaitPayFragment extends BaseFragment {
    @BindView(R.id.empty_fl)
    FrameLayout empty_fl;

    @BindView(R.id.recycle_repair_waitpay)
    RecyclerView recycleRepairWaitpay;
    private RepairPayAdapter repairPayAdapter;

    @Override
    protected void initData() {
        hashMap.clear();
        hashMap.put("token", getToken());
        hashMap.put("type", "2");
        net(false, false).post(1, Api.GetRepairList_URL, hashMap);
    }

    @Override
    protected void initView(View view) {
        //设置布局管理器
        recycleRepairWaitpay.setLayoutManager(new LinearLayoutManager(getContext()));
        repairPayAdapter = new RepairPayAdapter(getActivity());
        //设置Adapter
        recycleRepairWaitpay.setAdapter(repairPayAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_repair_waitpay;
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            GetRepairListBean getRepairListBean = gson.fromJson(data, GetRepairListBean.class);
            List<GetRepairListBean.DataBean.ListBean> list = getRepairListBean.getData().getList();
            if (list != null&list.size()>0) {
                repairPayAdapter.setData(list);
            }else{
                empty_fl.setVisibility(View.VISIBLE);
            }
        }
    }
}
