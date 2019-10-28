package com.sunshine.first.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.adapter.AllIndentAdapter;
import com.sunshine.first.adapter.StoreAllAdapter;
import com.sunshine.first.bean.AllIndentBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AllFragment extends BaseFragment {
    @BindView(R.id.reclcle_all_indent)
    RecyclerView reclcleAllIndent;
    Unbinder unbinder;
    private AllIndentAdapter allIndentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_allindent;
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
        reclcleAllIndent.setLayoutManager(linearLayoutManager);
        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        allIndentAdapter = new AllIndentAdapter(getActivity());
        //设置Adapter
        reclcleAllIndent.setAdapter(allIndentAdapter);
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
                allIndentAdapter.setDataList(list);
            }


        }
    }
}
