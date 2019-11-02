package com.sunshine.first.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abner.ming.base.utils.Logger;
import com.sunshine.first.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.bumptech.glide.Glide;
import com.sunshine.first.R;
import com.sunshine.first.adapter.GoodsAdapter;
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
    @BindView(R.id.iv_good_img_goods_details)
    ImageView iv_good_img_goods_details;

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
    @BindView(R.id.tv_store_descrip)
    TextView tvStoreDescrip;

    @BindView(R.id.rv_goods_details)
    RecyclerView rv_goods_details;
    @BindView(R.id.iv_goods_details_details)
    ImageView iv_goods_details_details;

    @BindView(R.id.tv_bottom_price_goods_details)
    TextView tv_bottom_price_goods_details;
    @BindView(R.id.tv_bottom_wholesale_price_goods_details)
    TextView tv_bottom_wholesale_price_goods_details;
    @BindView(R.id.tv_bottom_wholesale_num_goods_details)
    TextView tv_bottom_wholesale_num_goods_details;

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

        setTransparentForWindow(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goodsdetail;
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            goodsDeatilBean = gson.fromJson(data, GoodsDeatilBean.class);
            if (goodsDeatilBean != null && goodsDeatilBean.data != null) {
                GoodsDeatilBean.DataBean dataBean = goodsDeatilBean.data;
                if (dataBean.goods_images != null && dataBean.goods_images.size() > 0) {//设置图片
                    Glide.with(this).load(dataBean.goods_images.get(0)).into(iv_good_img_goods_details);
                }
                tvStoreDescrip.setText(dataBean.goods_describe + "");//商品描述
                tvStorePrice.setText("￥" + dataBean.retail_price + "");//市场价格
                tvTradePrice.setText("批发价 " + dataBean.wholesale_price);//批发价格

                Glide.with(this).load(dataBean.shop_img).into(iconStore);//商户logo

                tvStoreName.setText(dataBean.shop_name + "");//商户名称
                tvStoreNum.setText("商品数量:" + dataBean.goods_num);//商户名称
                if (dataBean.list != null && dataBean.list.size() > 0) {
                    GoodsAdapter goodsAdapter = new GoodsAdapter(this);
                    rv_goods_details.setAdapter(goodsAdapter);
                    goodsAdapter.addData(dataBean.list);
                }
                tv_bottom_price_goods_details.setText(dataBean.retail_price + "");//底部市场价格
                try {
                    double wholesale_price = Double.valueOf(dataBean.wholesale_price) * dataBean.wholesale_num;
                    tv_bottom_wholesale_price_goods_details.setText("￥：" + wholesale_price);//批发价格
                } catch (NumberFormatException e) {
                    Logger.d("市场价格转换", "错误");
                }

                tv_bottom_wholesale_num_goods_details.setText("批发购买（" + dataBean.wholesale_num + "件起）");//数量


                //商品详情
                if (dataBean.goods_details_image != null && dataBean.goods_details_image.size() > 0) {

                    Glide.with(this).load(dataBean.goods_details_image.get(0)).into(iv_goods_details_details);

                }
            }

        }
    }

    @OnClick({R.id.icon_back, R.id.btn_forget_yancode, R.id.ll_singleton_goods_details, R.id.ll_wholesale_goods_details})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_forget_yancode:
                if (goodsDeatilBean != null) {
                    intent = new Intent(this, MerchantShopActivity.class);
                    intent.putExtra("m_id", goodsDeatilBean.data.m_id);
                    startActivity(intent);
                }
                break;
            case R.id.ll_singleton_goods_details://单件购买
                if (goodsDeatilBean != null) {
                    intent = new Intent(this, PaymentActivity.class);
                    intent.putExtra("retail_price", goodsDeatilBean.data.retail_price + "");//零售价格
                    intent.putExtra("g_id", goodsDeatilBean.data.id + "");
                    startActivity(intent);
                }
                break;
            case R.id.ll_wholesale_goods_details://批发购买
                if (goodsDeatilBean != null) {
                    intent = new Intent(this, PaymentActivity.class);
                    intent.putExtra("retail_price", goodsDeatilBean.data.wholesale_price + "");//批发价格
                    intent.putExtra("g_id", goodsDeatilBean.data.id + "");
                    intent.putExtra("wholesale_num", goodsDeatilBean.data.wholesale_num);
                    intent.putExtra("type", 1);
                    startActivity(intent);
                }
                break;
        }
    }
}
