package com.sunshine.first.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.adapter.AllIndentAdapter;
import com.sunshine.first.bean.AllIndentBean;

import java.util.List;

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
        //设置布局管理器
        reclcleAllIndent.setLayoutManager(new LinearLayoutManager(getActivity()));
        allIndentAdapter = new AllIndentAdapter(getActivity());
        //设置Adapter
        reclcleAllIndent.setAdapter(allIndentAdapter);

        hashMap.clear();
        hashMap.put("token", getToken());
        net(false, false).post(2, Api.GetGoodsOrderList_URL, hashMap);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 2) {
            AllIndentBean allIndentBean = gson.fromJson(data, AllIndentBean.class);
            List<AllIndentBean.DataBean.ListBean> list = allIndentBean.getData().getList();
            if (list != null) {
                allIndentAdapter.setDataList(list);
            }
        }
    }
}
