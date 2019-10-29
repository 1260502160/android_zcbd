package com.sunshine.first.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunshine.first.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.bean.GoodsDeatilBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商品详情页面
 */
public class GoodsDetailActivity extends BaseAppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.icon_goods)
    ImageView iconGoods;
    @BindView(R.id.tv_store_price)
    TextView tvStorePrice;
    @BindView(R.id.tv_trade_price)
    TextView tvTradePrice;
    @BindView(R.id.icon_store)
    ImageView iconStore;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_store_num)
    TextView tvStoreNum;
    @BindView(R.id.btn_forget_yancode)
    Button btnForgetYancode;
    @BindView(R.id.rela_goodsdetail)
    RelativeLayout relaGoodsdetail;
    @BindView(R.id.view_fangzhurenzheng_two)
    View viewFangzhurenzhengTwo;
    @BindView(R.id.tv_store_deatil)
    TextView tvStoreDeatil;
    @BindView(R.id.tv_store_descrip)
    TextView tvStoreDescrip;
    @BindView(R.id.icon_goods_two)
    ImageView iconGoodsTwo;
    private Gson gson;
    private GoodsDeatilBean goodsDeatilBean;


    @Override
    protected void initData() {

        int id = getIntent().getIntExtra("id", -1);
        Map<String, String> map = new HashMap<>();
        map.put("token", getToken());
        map.put("id", id + "");
        net(true, false).post(1, Api.GoodsListDeatil_URL, map);
        Log.i("ssss", "net");
    }

    @Override
    protected void initView() {


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goodsdetail;
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {

            gson = new Gson();
            goodsDeatilBean = gson.fromJson(data, GoodsDeatilBean.class);
            Glide.with(GoodsDetailActivity.this).load(goodsDeatilBean.getData().getGoods_details_image()).into(iconGoods);
            tvStoreDescrip.setText(goodsDeatilBean.getData().getGoods_describe());
            tvStorePrice.setText(goodsDeatilBean.getData().getRetail_price() + "");
            tvTradePrice.setText(goodsDeatilBean.getData().getWholesale_price() + "");
            Glide.with(GoodsDetailActivity.this).load(goodsDeatilBean.getData().getShop_img()).into(iconStore);
            tvStoreName.setText(goodsDeatilBean.getData().getShop_name());
            tvStoreNum.setText(goodsDeatilBean.getData().getGoods_num() + "");

        }
    }

    @OnClick({R.id.icon_back, R.id.btn_forget_yancode, R.id.ll_singleton_goods_details})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_forget_yancode:
                intent = new Intent(this, MerchantShopActivity.class);
                intent.putExtra("m_id", goodsDeatilBean.getData().getM_id());
                startActivity(intent);
                break;
            case R.id.ll_singleton_goods_details:
                intent = new Intent(this, PaymentActivity.class);
                intent.putExtra("retail_price", goodsDeatilBean.getData().getRetail_price() + "");
                intent.putExtra("g_id", goodsDeatilBean.getData().getId() + "");
                startActivity(intent);
                break;
        }
    }
}
