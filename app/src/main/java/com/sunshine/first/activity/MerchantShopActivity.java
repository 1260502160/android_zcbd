package com.sunshine.first.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.adapter.GoodsAdapter;
import com.sunshine.first.bean.MerchantShopBean;

import butterknife.BindView;

/**
 * 商家店铺页面
 */
public class MerchantShopActivity extends BaseAppCompatActivity {
    @BindView(R.id.rv_merchant_shop)
    RecyclerView rv_merchant_shop;
    private GoodsAdapter merchantShopAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_merchant_shop;
    }

    @Override
    protected void initView() {
        setDefaultTitle("商家店铺");
        rv_merchant_shop.setLayoutManager(new GridLayoutManager(this, 2));
        merchantShopAdapter = new GoodsAdapter(this);
        rv_merchant_shop.setAdapter(merchantShopAdapter);

    }

    @Override
    protected void initData() {
        int m_id = getIntent().getIntExtra("m_id", 0);
        hashMap.put("token", getToken());
        hashMap.put("m_id", m_id + "");//商家id
        hashMap.put("page", page + "");//页码默认1
        hashMap.put("perpage", perpage + "");//每页数据 默认10
//        hashMap.put("keyword", );//关键字搜索


        net(true, false).post(1, Api.GetShopInfoURL, hashMap);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            if (!TextUtils.isEmpty(data)) {
                MerchantShopBean merchantShopBean = gson.fromJson(data, MerchantShopBean.class);
                if (merchantShopBean != null && merchantShopBean.data != null) {
                    merchantShopAdapter.addData(merchantShopBean.data.list);
                }
            }
        }
    }
}
