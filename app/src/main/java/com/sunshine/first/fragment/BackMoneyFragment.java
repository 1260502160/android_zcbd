package com.sunshine.first.fragment;

import android.os.Bundle;
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
import com.sunshine.first.adapter.BackMoneyAdapter;
import com.sunshine.first.adapter.PayMoneyAdapter;
import com.sunshine.first.bean.AllIndentBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
退款订单
 */
public class BackMoneyFragment extends BaseFragment {
    @BindView(R.id.paymoney_recycle)
    RecyclerView paymoneyRecycle;
    Unbinder unbinder;
    private BackMoneyAdapter backMoneyAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_paymoney;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        //设置布局管理器
        paymoneyRecycle.setLayoutManager(linearLayoutManager);
        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        backMoneyAdapter = new BackMoneyAdapter(getActivity());
        //设置Adapter
        paymoneyRecycle.setAdapter(backMoneyAdapter);
        String token = SharePreferenceHelper.getInstance(getContext()).getString("token", "");
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        net(false,false).post(2,Api.GetGoodsOrderList_URL,map);
    }
    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==2){
            Gson gson = new Gson();
            AllIndentBean allIndentBean = gson.fromJson(data, AllIndentBean.class);
            List<AllIndentBean.DataBean.ListBean> list = allIndentBean.getData().getList();
            if (list!=null){
                backMoneyAdapter.setDataList(list);
            }


        }
    }
}
