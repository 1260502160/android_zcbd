package com.sunshine.first.activity;


import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.PayDetailsBean;

import butterknife.BindView;


/**
 * 缴费明细
 */
public class PayDeatilActivity extends BaseAppCompatActivity {

    @BindView(R.id.iv_head_pay_details)
    ImageView iv_head_pay_details;
    @BindView(R.id.tv_name_pay_details)
    TextView tv_name_pay_details;
    @BindView(R.id.tv_money_pay_details)
    TextView tv_money_pay_details;

    @BindView(R.id.tv_state_pay_details)
    TextView tv_state_pay_details;//当前状态
    @BindView(R.id.tv_pay_type_pay_details)
    TextView tv_pay_type_pay_details;//支付方式
    @BindView(R.id.tv_money_pay_type_details)
    TextView tv_money_pay_type_details;//叫的什么费用
    @BindView(R.id.tv_car_number_pay_details)
    TextView tv_car_number_pay_details;//车牌号码
    @BindView(R.id.tv_content_pay_details)
    TextView tv_content_pay_details;//缴费说明
    @BindView(R.id.tv_time_pay_details)
    TextView tv_time_pay_details;//缴费时间
    @BindView(R.id.tv_pay_number_pay_details)
    TextView tv_pay_number_pay_details;//订单编号

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_deatil;
    }

    @Override
    protected void initView() {
        setDefaultTitle("缴费明确");


    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        hashMap.clear();
        hashMap.put("token", getToken());
        hashMap.put("id", id + "");

        net(true, false).post(1, Api.GetFinanceDetail_URL, hashMap);

    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1 && !TextUtils.isEmpty(data)) {
            PayDetailsBean payDetailsBean = gson.fromJson(data, PayDetailsBean.class);
            if (payDetailsBean != null && payDetailsBean.getData() != null) {
                PayDetailsBean.DataBean pbdb = payDetailsBean.getData();
//                tv_name_pay_details.setText(pbdb.);
                tv_money_pay_details.setText(pbdb.getProperty_fee() + "");
//                tv_state_pay_details.setText(pbdb. + "");
                tv_pay_type_pay_details.setText(pbdb.getPay_type() + "");
                tv_money_pay_type_details.setText(pbdb.getType() + "");
                tv_car_number_pay_details.setText(pbdb.getType() + "");
                tv_time_pay_details.setText(pbdb.getPay_time() + "");
                tv_pay_number_pay_details.setText(pbdb.getOrder_id() + "");

            }

        }
    }
}
