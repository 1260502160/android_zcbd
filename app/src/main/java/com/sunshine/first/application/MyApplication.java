package com.sunshine.first.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.abner.ming.base.AbnerApplication;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author wavewave
 * @CreateDate: 2019-11-02 09:33
 * @Description:
 * @Version: 1.0
 */
public class MyApplication extends AbnerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        regToWx();
    }

    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    public static final String APP_ID = "wxc84f952138a6dd92";
    public static final String APP_SECRET= "25bb5a5753be46e7658c1bc4e984726b";

    // IWXAPI 是第三方app和微信通信的openApi接口

    public static IWXAPI mWxApi;//微信api

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        mWxApi = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        mWxApi.registerApp(APP_ID);

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                mWxApi.registerApp(APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

    }
}
