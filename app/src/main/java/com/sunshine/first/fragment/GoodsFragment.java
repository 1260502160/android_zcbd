package com.sunshine.first.fragment;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.adapter.GoodsAdapter;
import com.sunshine.first.bean.GoodsListBean;

import butterknife.BindView;

/**
 * 商品列表
 */
public class GoodsFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    @BindView(R.id.rv_f_goods)
    RecyclerView rv_f_goods;
    private GoodsAdapter goodsAdapter;

    public GoodsFragment() {
    }

    public static GoodsFragment newInstance(String param1, String param2) {
        GoodsFragment fragment = new GoodsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_goods;
    }

    @Override
    protected void initView(View view) {
        rv_f_goods.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        goodsAdapter = new GoodsAdapter(getActivity());
        rv_f_goods.setAdapter(goodsAdapter);
    }

    @Override
    protected void initData() {
        hashMap.put("token", getToken());
        hashMap.put("cla_id", mParam1);//分类id 默认0 全部
        hashMap.put("page", page + "");//分类id 默认0 全部
        hashMap.put("perpage", perpage + "");//每页数据 默认10

        net(true, false).post(1, Api.GoodsList_URL, hashMap);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            GoodsListBean goodsListBean = gson.fromJson(data, GoodsListBean.class);
            if (goodsListBean != null && goodsListBean.getData() != null) {
                GoodsListBean.DataBean listBeanData = goodsListBean.getData();
                if (listBeanData.getList() != null && listBeanData.getList().size() > 0) {
                    goodsAdapter.addData(listBeanData.getList());
                }
            }
        }
    }
}
