package com.sunshine.first.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.abner.ming.base.model.Api;
import com.bumptech.glide.Glide;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.GoodsOrderDetailsBean;

import butterknife.BindView;

/**
 * 订单
 * 已付款
 */
public class OrderPaidActivity extends BaseAppCompatActivity {

    @BindView(R.id.tvaddress)
    TextView tvaddress;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_infact_address)
    TextView tvInfactAddress;
    @BindView(R.id.icon_all_indent)
    ImageView iconAllIndent;
    @BindView(R.id.tv_pay_info)
    TextView tvPayInfo;
    @BindView(R.id.tv_indent_status)
    TextView tvIndentStatus;
    @BindView(R.id.tv_indent_num)
    TextView tvIndentNum;
    @BindView(R.id.tv_indent_time)
    TextView tvIndentTime;
    @BindView(R.id.tv_indenttime)
    TextView tvIndenttime;
    @BindView(R.id.tv_indent_number)
    TextView tvIndentNumber;
    @BindView(R.id.tv_wait_pay)
    TextView tvWaitPay;
    @BindView(R.id.tv_descript)
    TextView tvDescript;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_count)
    TextView tvCount;

    private int orderId;//订单id

    @Override
    public int getLayoutId() {
        return R.layout.layout_indent_deatil_two;
    }

    @Override
    protected void initView() {
        orderId = getIntent().getIntExtra("id", 0);
        getAddressArray();
    }

    @Override
    protected void initData() {

        getAddressArray();
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            GoodsOrderDetailsBean goodsOrderDetailsBean = gson.fromJson(data, GoodsOrderDetailsBean.class);
            if (goodsOrderDetailsBean != null && goodsOrderDetailsBean.getData() != null) {
                GoodsOrderDetailsBean.DataBean detailsBeanData = goodsOrderDetailsBean.getData();
                tvName.setText(detailsBeanData.getAddress_name() + "  " + detailsBeanData.getAddress_mobile());
                tvInfactAddress.setText(detailsBeanData.getAddress() + "");
                tvPrice.setText("￥" + detailsBeanData.getMoney());
                tvCount.setText("x" + detailsBeanData.getGoods_num() + "");
                tvDescript.setText(detailsBeanData.getGoods_describe() + "");
                Glide.with(this).load(detailsBeanData.getGoods_image() + "").into(iconAllIndent);
                tvIndentNumber.setText(detailsBeanData.getOrder_no() + "");
                tvIndenttime.setText(detailsBeanData.getCreated_at() + "");
//                tvNeedPayMoney.setText(detailsBeanData.getOrder_money() + "");

            }

        }
    }


    private void getAddressArray() {
        hashMap.clear();
        hashMap.put("token", getToken());
        hashMap.put("id", orderId + "");
        net(true, false).post(1, Api.GetGoodsOrderDetail_URL, hashMap);
    }
}
