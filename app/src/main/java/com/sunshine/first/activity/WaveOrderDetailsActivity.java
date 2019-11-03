package com.sunshine.first.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.abner.ming.base.model.Api;
import com.abner.ming.base.utils.Logger;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.application.MyApplication;
import com.sunshine.first.bean.AddAddressBean;
import com.sunshine.first.bean.GoodsOrderDetailsBean;
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
 * 订单列表页面跳转过去详情
 * 立即支付
 */
public class WaveOrderDetailsActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_address_name_phone_payment)
    TextView tv_address_name_phone_payment;
    @BindView(R.id.tv_address_address_content)
    TextView tv_address_address_content;
    @BindView(R.id.tv_address_title_payment)
    TextView tv_address_title_payment;
    @BindView(R.id.tv_content_payment)
    TextView tv_content_payment;
    @BindView(R.id.tv_order_code_details)
    TextView tv_order_code_details;
    @BindView(R.id.tv_order_time_details)
    TextView tv_order_time_details;
    @BindView(R.id.tv_close_time_details)
    TextView tv_close_time_details;


    @BindView(R.id.tv_payment_money)
    TextView tv_payment_money;
    @BindView(R.id.tv_payment_num)
    TextView tv_payment_num;
    @BindView(R.id.tv_price_payment)
    TextView tv_price_payment;
    @BindView(R.id.iv_img_payment)
    ImageView iv_img_payment;

    @BindView(R.id.zfpay_check_balance)
    RadioButton zfpay_check_balance;
    @BindView(R.id.wexin_check_balance)
    RadioButton wexin_check_balance;
    private GoodsOrderDetailsBean.DataBean godb;

    private IWXAPI api;

    /**
     * @param activity
     * @param order_id 订单id
     */
    public static void startWaveOrderDetailsActivity(Context activity, int order_id) {
        Intent intent = new Intent(activity, WaveOrderDetailsActivity.class);
        intent.putExtra("order_id", order_id);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wave_order_details;
    }

    @Override
    protected void initView() {
        setDefaultTitle("订单详情页面");
    }

    @Override
    protected void initData() {

        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID);

        int order_id = getIntent().getIntExtra("order_id", 0);
        hashMap.clear();
        hashMap.put("token", getToken());
        hashMap.put("id", order_id + "");
        net(true, false).post(1, Api.GetGoodsOrderDetail_URL, hashMap);

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

    private int num = 1;

    private int pay_type;
    private int payType = 1; //1零售 2是批发购买
    private int wholesale_num; //批发数量
    private String retail_price, g_id;//商品id
    private String money;//总价格

    @OnClick({R.id.rl_address, R.id.tv_payment_jian_count, R.id.tv_payment_add_count, R.id.tv_pay_payment,
            R.id.tv_pay_close_payment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_address://选择地址
//                Intent intent = new Intent(this, AddressListActivity.class);
//                startActivityForResult(intent, 100);
                break;
            case R.id.tv_payment_add_count://加数量
//                num++;
//                tv_payment_num.setText(num + "");
//                setPaymentMoney();
                break;
            case R.id.tv_payment_jian_count://减数量
//                if (payType == 2) {
//                    if (num > wholesale_num) {
//                        num--;
//                        tv_payment_num.setText(num + "");
//                        setPaymentMoney();
//                    }
//                } else {
//                    if (num > 1) {
//                        num--;
//                        tv_payment_num.setText(num + "");
//                        setPaymentMoney();
//                    }
//                }
                break;
            case R.id.tv_pay_payment://支付
                pay_type = 1;
                if (zfpay_check_balance.isChecked()) {
                    pay_type = 2;
                }
                hashMap.clear();
                hashMap.put("token", getToken());//用户标识
                hashMap.put("id", godb.getId() + "");//订单id
                hashMap.put("type", "1");//

                if (pay_type == 2) {//4微信  2支付宝
                    net(true, false).post(2, Api.Alipay_URL, hashMap);
                } else {//微信支付
                    net(true, false).post(4, Api.Wechat_URL, hashMap);
                }

                break;
            case R.id.tv_pay_close_payment://取消订单
                hashMap.clear();
                hashMap.put("token", getToken());//用户标识
                hashMap.put("id", godb.getId() + "");//订单id
                net(true, false).post(10, Api.CloseOrder_URL, hashMap);
                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1 && !TextUtils.isEmpty(data)) {
            GoodsOrderDetailsBean goodsOrderDetailsBean = gson.fromJson(data, GoodsOrderDetailsBean.class);
            if (goodsOrderDetailsBean != null && goodsOrderDetailsBean.getData() != null) {
                godb = goodsOrderDetailsBean.getData();
                tv_address_name_phone_payment.setText(godb.getAddress_name() + "  " + godb.getAddress_mobile());//名字和电话
                tv_address_address_content.setText(godb.getAddress() + "");//收货地址
                Glide.with(this).load(godb.getGoods_image()).into(iv_img_payment);//商品图片
                tv_content_payment.setText(godb.getGoods_describe() + "");//商品描述
                tv_price_payment.setText("¥" + godb.getMoney() + "");//商品单价
                tv_order_code_details.setText("订单编号：" + godb.getOrder_no());
                tv_order_time_details.setText("下单时间：" + godb.getCreated_at());
                tv_payment_money.setText("¥" + godb.getOrder_money());
                tv_close_time_details.setText(godb.getPay_end_time());//结束时间

                g_id = godb.getGoods_id() + "";
                num = godb.getGoods_num();
                money = godb.getOrder_money();
                retail_price = godb.getMoney();

            }
        }
        if (type == 2) {//获取订单后支付宝支付
            // TODO: 2019/10/29 跳转 支付宝 或者微信支付
            final String orderInfo = data;   // 订单信息
            Runnable payRunnable = new Runnable() {

                @Override
                public void run() {
                    PayTask alipay = new PayTask(WaveOrderDetailsActivity.this);
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
        if (type == 10) {//取消订单
            AddAddressBean addAddressBean = gson.fromJson(data, AddAddressBean.class);
            if (addAddressBean != null) {
                ToastManage.s(this, addAddressBean.message + "");
                if ("200".equals(addAddressBean.error_code)) {
                    finish();
                }
            }
        }
    }

    //设置总价格
    private void setPaymentMoney() {
        try {
            if (!TextUtils.isEmpty(retail_price)) {
                Double aDouble = Double.valueOf(retail_price);
                money = String.valueOf(aDouble * num);
                tv_payment_money.setText("¥" + money);
            }
        } catch (Exception e) {
            Logger.e("setPaymentMoney", "e:" + e.toString());
        }

    }

    private int addressId = -1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == 100 && resultCode == 101) {//选择收获地址
//            "addressId", addre
//            "name", addressBea
//            "phoneNumber", add
//            "addressDetails",
//
            addressId = data.getIntExtra("addressId", -1);
            if (addressId != -1) {
                String name = data.getStringExtra("name");
                String phoneNumber = data.getStringExtra("phoneNumber");
                String addressDetails = data.getStringExtra("addressDetails");
                tv_address_name_phone_payment.setText(name + "   " + phoneNumber);
                tv_address_address_content.setText(addressDetails + "");

                tv_address_title_payment.setVisibility(View.GONE);
                tv_address_name_phone_payment.setVisibility(View.VISIBLE);
                tv_address_address_content.setVisibility(View.VISIBLE);

            }
        }
    }
}
