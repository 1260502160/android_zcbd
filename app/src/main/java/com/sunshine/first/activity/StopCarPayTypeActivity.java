package com.sunshine.first.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.base.model.Api;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.application.MyApplication;
import com.sunshine.first.bean.ParkingChargeBean;
import com.sunshine.first.bean.WeChatPaymentBean;
import com.sunshine.first.utils.SharePreferenceHelper;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StopCarPayTypeActivity extends BaseAppCompatActivity {
    @BindView(R.id.text_carnumber)
    TextView textCarnumber;
    @BindView(R.id.view_stopcar_one)
    View viewStopcarOne;
    @BindView(R.id.text_car_number)
    TextView textCarNumber;
    @BindView(R.id.icon_carnumber)
    ImageView iconCarnumber;
    @BindView(R.id.text_yucunzhouqi)
    TextView textYucunzhouqi;
    @BindView(R.id.text_yczhouqi)
    TextView textYczhouqi;
    @BindView(R.id.icon_yczhouqi)
    ImageView iconYczhouqi;
    @BindView(R.id.rela_paymoney)
    RelativeLayout relaPaymoney;
    @BindView(R.id.view_pay_money)
    View viewPayMoney;
    @BindView(R.id.wx)
    TextView wx;
    @BindView(R.id.wexin_check_balance)
    RadioButton wexinCheckBalance;
    @BindView(R.id.zfpay_check_balance)
    RadioButton zfpayCheckBalance;
    @BindView(R.id.btn_rightpay)
    Button btnRightpay;
    private int carnumber;
    private int cycle;
    private int id;
    private int type;

    private IWXAPI api;
    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setDefaultTitle("支付方式");


        id = getIntent().getIntExtra("id", 0);
        type = getIntent().getIntExtra("type", 0);
        String plate_num = getIntent().getStringExtra("plate_num");
        String pay_money = getIntent().getStringExtra("pay_money");


        textCarNumber.setText(plate_num + "");
        textYczhouqi.setText("￥" + pay_money + "");

        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID);
    }

    @Override
    protected void initData() {
        carnumber = getIntent().getIntExtra("carnumber", -1);
        cycle = getIntent().getIntExtra("cycle", -1);
        btnRightpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hashMap.clear();
                hashMap.put("token", getToken());//用户标识
                hashMap.put("id", id + "");//
                hashMap.put("type", type+"");//

                if (zfpayCheckBalance.isChecked()) {//支付宝支付
                    net(true, false).post(2, Api.Alipay_URL, hashMap);
                } else {//微信支付
                    net(true, false).post(4, Api.Wechat_URL, hashMap);
                }
//                String token = SharePreferenceHelper.getInstance(StopCarPayTypeActivity.this).getString("token", "");
//                Map<String, String> map = new HashMap<>();
//                map.put("token", token);
//                map.put("car_id", "1");
//                map.put("comm_id", "1");
//                map.put("car_pay_type", cycle + "");
//                map.put("plate_num", carnumber + "");
//                net(false, false).post(1, Api.ParkingCharge_URL, map);
            }
        });


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
//        if (type == 1) {
//            Gson gson = new Gson();
//            ParkingChargeBean parkingChargeBean = gson.fromJson(data, ParkingChargeBean.class);
//            ParkingChargeBean.DataBean parkingChargeBeanData = parkingChargeBean.getData();
//            Toast.makeText(StopCarPayTypeActivity.this, parkingChargeBean.getMessage().toString(), Toast.LENGTH_SHORT).show();
//        }
        if (type == 2) {//获取订单后支付宝支付
            // TODO: 2019/10/29 跳转 支付宝 或者微信支付
            final String orderInfo = data;   // 订单信息
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(StopCarPayTypeActivity.this);
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

        if (type == 4) {//获取订单后微信支付

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
}
