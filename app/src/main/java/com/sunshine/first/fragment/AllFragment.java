package com.sunshine.first.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.adapter.AllIndentAdapter;
import com.sunshine.first.bean.AllIndentBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的订单
 * 全部订单
 */
public class AllFragment extends BaseFragment {
    @BindView(R.id.empty_fl)
    FrameLayout empty_fl;//空页面

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

        int status = getArguments().getInt("status", 0);
        hashMap.clear();
        hashMap.put("token", getToken());
        hashMap.put("status", status + "");//订单状态0全部1待付款2已付款3退款4已关闭
        hashMap.put("page", page + "");//订单状态0全部1待付款2已付款3退款4已关闭
        hashMap.put("perpage", 30 + "");//订单状态0全部1待付款2已付款3退款4已关闭
        net(false, false).post(2, Api.GetGoodsOrderList_URL, hashMap);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 2) {
            AllIndentBean allIndentBean = gson.fromJson(data, AllIndentBean.class);
            if (allIndentBean.getData() != null && allIndentBean.getData().getList() != null && allIndentBean.getData().getList().size() > 0) {
                allIndentAdapter.setDataList(allIndentBean.getData().getList());
            } else {
                empty_fl.setVisibility(View.VISIBLE);
            }
        }
    }
}
