package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.base.model.Api;
import com.abner.ming.base.utils.Logger;
import com.alipay.sdk.app.PayTask;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.application.MyApplication;
import com.sunshine.first.bean.PaymentBean;
import com.sunshine.first.bean.WeChatPaymentBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 购买支付页面
 */
public class PaymentActivity extends BaseAppCompatActivity {
    @BindView(R.id.tv_address_name_phone_payment)
    TextView tv_address_name_phone_payment;
    @BindView(R.id.tv_address_address_content)
    TextView tv_address_address_content;

    @BindView(R.id.tv_payment_jian_count)
    TextView tv_payment_jian_count;
    @BindView(R.id.tv_payment_add_count)
    TextView tv_payment_add_count;
    @BindView(R.id.tv_payment_money)
    TextView tv_payment_money;
    @BindView(R.id.tv_payment_num)
    TextView tv_payment_num;
    @BindView(R.id.tv_all_payment_money)
    TextView tv_all_payment_money;
    @BindView(R.id.tv_price_payment)
    TextView tv_price_payment;
    @BindView(R.id.et_remake_payment)
    EditText et_remake_payment;

    @BindView(R.id.zfpay_check_balance)
    RadioButton zfpay_check_balance;
    @BindView(R.id.wexin_check_balance)
    RadioButton wexin_check_balance;

    private int addressId;
    private int num = 1;
    private String retail_price, g_id;
    private double money;
    private int pay_type;
    private IWXAPI api;


    private int payType = 1; //1零售 2是批发购买
    private int wholesale_num; //批发数量

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initView() {
        setDefaultTitle("支付");

        wholesale_num = getIntent().getIntExtra("wholesale_num", 1);
        payType = getIntent().getIntExtra("type", 1);
        num = wholesale_num;//设置默认数量

        retail_price = getIntent().getStringExtra("retail_price");

        g_id = getIntent().getStringExtra("g_id");

        tv_payment_num.setText(num + "");

        tv_price_payment.setText("¥" + retail_price);

        setPaymentMoney();

        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID);

    }

    @Override
    protected void initData() {
        setPaymentMoney();
    }

    @OnClick({R.id.rl_address, R.id.tv_payment_jian_count, R.id.tv_payment_add_count, R.id.tv_pay_payment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_address://选择地址
                Intent intent = new Intent(this, AddressListActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.tv_payment_add_count://加数量
                num++;
                tv_payment_num.setText(num + "");
                setPaymentMoney();
                break;
            case R.id.tv_payment_jian_count://减数量
                if (payType == 2) {
                    if (num > wholesale_num) {
                        num--;
                        tv_payment_num.setText(num + "");
                        setPaymentMoney();
                    }
                } else {
                    if (num > 1) {
                        num--;
                        tv_payment_num.setText(num + "");
                        setPaymentMoney();
                    }
                }
            case R.id.tv_pay_payment://支付
                hashMap.clear();
                hashMap.put("token", getToken());//用户标识
                hashMap.put("g_id", g_id);//商品id
                hashMap.put("a_id", addressId + "");//地址id
                hashMap.put("g_num", num + "");//商品数量
                hashMap.put("order_money", money + "");//订单金额\

                hashMap.put("type", payType + "");//购买类型 1零售2批发

                pay_type = 1;
                if (zfpay_check_balance.isChecked()) {
                    pay_type = 2;
                }
                hashMap.put("pay_type", pay_type + "");//支付方式1微信2支付宝

                hashMap.put("note", et_remake_payment.getText().toString().trim() + "");//备注

                net(true, false).post(1, Api.CreateGoodsOrder_URL, hashMap);
                break;
        }
    }

    private final int SDK_PAY_FLAG = 666;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
//                    Result result = new PayResult((String) msg.obj);
//                    Toast.makeText(DemoActivity.this, result.getResult(),
//                            Toast.LENGTH_LONG).show();

                    //这里接收支付宝的回调信息
                    //需要注意的是，支付结果一定要调用自己的服务端来确定，不能通过支付宝的回调结果来判断
                    hashMap.clear();
                    hashMap.put("token", getToken());//用户标识
                    net(true, false).post(3, Api.Alipaynotify_URL, hashMap);
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {//创建订单成功
            PaymentBean paymentBean = gson.fromJson(data, PaymentBean.class);
            if (paymentBean != null && paymentBean.getData() != null) {
                hashMap.clear();
                hashMap.put("token", getToken());//用户标识
                hashMap.put("id", paymentBean.getData().getOrder_id());//
                hashMap.put("type", paymentBean.getData().getType() + "");//

                if (pay_type == 2) {//1微信2支付宝
                    net(true, false).post(2, Api.Alipay_URL, hashMap);
                } else {//微信支付
                    net(true, false).post(3, Api.Wechat_URL, hashMap);
                }
            }
        }
        if (type == 2) {//获取订单后支付宝支付
            // TODO: 2019/10/29 跳转 支付宝 或者微信支付
            final String orderInfo = data;   // 订单信息
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(PaymentActivity.this);
                    Map<String, String> result = alipay.payV2(orderInfo, true);

                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();

        }
        if (type == 3) {//获取订单后微信支付

            String content = data;// (这个是服务端返回的订单信息)
            WeChatPaymentBean weChatPaymentBean = gson.fromJson(data, WeChatPaymentBean.class);
            if (weChatPaymentBean != null && weChatPaymentBean.getData() != null) {
                WeChatPaymentBean.DataBeanX beanData = weChatPaymentBean.getData();
                WeChatPaymentBean.DataBeanX.DataBean dataData = beanData.getData();
                String jsonWeChat = gson.toJson(dataData);
                if (!TextUtils.isEmpty(jsonWeChat)) {
                    JSONObject json = null;
                    try {
                        json = new JSONObject(jsonWeChat);
                        if (null != json && !json.has("retcode")) {
                            PayReq req = new PayReq();
                            //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                            req.appId = json.getString("appid");
                            req.partnerId = json.getString("partnerid");
                            req.prepayId = json.getString("prepayid");
                            req.nonceStr = json.getString("noncestr");
                            req.timeStamp = json.getString("timestamp");
//                            req.packageValue = json.getString("package");
                            req.packageValue = "Sign=WXPay";
                            req.sign = json.getString("sign");
//                            req.extData = "app data"; // optional
                            Toast.makeText(PaymentActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                            api.sendReq(req);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    //设置总价格
    private void setPaymentMoney() {
        try {
            if (!TextUtils.isEmpty(retail_price)) {
                Double aDouble = Double.valueOf(retail_price);
                money = aDouble * num;
                tv_payment_money.setText("¥" + money);
                tv_all_payment_money.setText("¥" + money);
            }
        } catch (Exception e) {
            Logger.e("setPaymentMoney", "e:" + e.toString());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 100 && resultCode == 101) {
//            "addressId", addre
//            "name", addressBea
//            "phoneNumber", add
//            "addressDetails",
//
            addressId = data.getIntExtra("addressId", 0);
            String name = data.getStringExtra("name");
            String phoneNumber = data.getStringExtra("phoneNumber");
            String addressDetails = data.getStringExtra("addressDetails");
            tv_address_name_phone_payment.setText(name + "   " + phoneNumber);
            tv_address_address_content.setText(addressDetails + "");
        }
    }
}
