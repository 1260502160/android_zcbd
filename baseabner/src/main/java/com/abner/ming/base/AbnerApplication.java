package com.abner.ming.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.abner.ming.base.utils.CacheUtils;
import com.facebook.drawee.backends.pipeline.Fresco;


public class AbnerApplication extends MultiDexApplication {
    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
        Fresco.initialize(this);
        CacheUtils.getCacheUtils().init(this);
//        regToWx();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static Context getContext() {
        return app;
    }

//    // APP_ID 替换为你的应用从官方网站申请到的合法appID
//    public static final String APP_ID = "wxc84f952138a6dd92";
//
//    // IWXAPI 是第三方app和微信通信的openApi接口
//    private IWXAPI api;
//
//    private void regToWx() {
//        // 通过WXAPIFactory工厂，获取IWXAPI的实例
//        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
//
//        // 将应用的appId注册到微信
//        api.registerApp(APP_ID);
//
//        //建议动态监听微信启动广播进行注册到微信
//        registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                // 将该app注册到微信
//                api.registerApp(APP_ID);
//            }
//        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
//
//    }
}
