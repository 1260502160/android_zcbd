package com.sunshine.first.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.abner.ming.base.AbnerApplication;
import com.sunshine.first.R;
import com.sunshine.first.application.MyApplication;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author wavewave
 * @CreateDate: 2019-11-01 21:01
 * @Description:
 * @Version: 1.0
 */
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_wxpay_entry);
        api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        //0成功 -1支付出错 -2用户取消支付
        //-1支付出错可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            //支付成功  TODO
        }
        Log.d("WXPayEntryActivity", "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            //这里肯定不能是像上面的DEMO一样弹出对话框了，而是通知我们发起支付调用的页面
            //然后及时finish掉这个页面，贴个伪代码：
//            sendPayNotice();
            finish();
        }
    }
}
