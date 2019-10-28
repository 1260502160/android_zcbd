package com.sunshine.first.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
订单详情
 */
public class IntentDetailActivity extends BaseAppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.text_forget)
    TextView textForget;
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
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_intent_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

}
