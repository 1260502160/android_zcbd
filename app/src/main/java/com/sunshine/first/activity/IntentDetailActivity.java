package com.sunshine.first.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abner.ming.base.model.Api;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.AddAddressBean;
import com.sunshine.first.bean.GoodsOrderDetailsBean;

import butterknife.BindView;
import butterknife.OnClick;

/*
订单详情
 */
public class IntentDetailActivity extends BaseAppCompatActivity {

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
    @BindView(R.id.tv_need_pay)
    TextView tvNeedPay;
    @BindView(R.id.tv_need_pay_money)
    TextView tvNeedPayMoney;
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
    @BindView(R.id.btn_cancel_indent)
    Button btnCancelndent;
    @BindView(R.id.btn_right_indent)
    Button btnRightIndent;
    private String id;//订单id

    @Override
    public int getLayoutId() {
        return R.layout.activity_intent_detail;
    }

    @Override
    protected void initView() {
        setDefaultTitle("订单详情");
    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0) + "";
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
                tvNeedPayMoney.setText(detailsBeanData.getOrder_money() + "");

            }

        } else if (type == 2) {
            AddAddressBean addAddressBean = gson.fromJson(data, AddAddressBean.class);
            if (addAddressBean != null) {
                ToastManage.s(this, addAddressBean.message + "");
                finish();
            }
        }
    }

    @OnClick(R.id.btn_cancel_indent)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel_indent://取消订单
                hashMap.clear();
                hashMap.put("token", getToken());//用户标识
                hashMap.put("id", id);//订单id
                net(true, false).post(2, Api.CloseOrder_URL, hashMap);
                break;
            case R.id.btn_right_indent://立即支付
                ToastManage.s(this, "支付页面!");
                break;
        }
    }

    private void getAddressArray() {
        hashMap.clear();
        hashMap.put("token", getToken());
        hashMap.put("id", id);
        net(true, false).post(1, Api.GetGoodsOrderDetail_URL, hashMap);
    }
}
