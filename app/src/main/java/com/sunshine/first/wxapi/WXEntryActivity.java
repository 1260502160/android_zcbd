package com.sunshine.first.wxapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.MainActivity;
import com.sunshine.first.activity.RegisterActivity;
import com.sunshine.first.application.MyApplication;
import com.sunshine.first.bean.WxLoginBean;
import com.sunshine.first.utils.SharePreferenceHelper;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 微信登陆
 */
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private static OkHttpClient okHttpClient;
    private static FormBody build;
    private static Gson gson;
    private static WxLoginBean wxLoginBean;
    private ProgressDialog mProgressDialog;
    private IWXAPI mApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApi = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID, true);
        mApi.handleIntent(this.getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {


    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.d("WXEntryActivity onResp", baseResp.errCode + "");
        //登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                //获取accesstoken
                getAccessToken(code);
                Log.d("WXEntryActivity login", code.toString() + "");
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                finish();
                break;
            default:
                finish();
                break;
        }


    }

    private void createProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//转盘
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setTitle("提示");
        mProgressDialog.setMessage("登录中，请稍后");
        mProgressDialog.show();
    }

    private void getAccessToken(String code) {
        createProgressDialog();
        //获取授权
        StringBuffer loginUrl = new StringBuffer();
        loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=")
                .append(MyApplication.APP_ID)
                .append("&secret=")
                .append(MyApplication.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        Log.d("urlurl", loginUrl.toString());

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(loginUrl.toString())
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("fan12", "onFailure: ");
                mProgressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseInfo = response.body().string();
                Log.d("fan12", "onResponse: " + responseInfo);
                String access = null;
                String openId = null;
                try {
                    JSONObject jsonObject = new JSONObject(responseInfo);
                    access = jsonObject.getString("access_token");
                    openId = jsonObject.getString("openid");

                    okHttpPost(Api.WXLOGIN_URL, openId);//                    net(false,fokHttpPostalse).post(1,Api.WXLOGIN_URL,hashMap);
//
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getUserInfo(access, openId);
            }
        });
    }

    private void getUserInfo(String access, String openid) {
        String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access + "&openid=" + openid;
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(getUserInfoUrl)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("fan12", "onFailure: ");
                mProgressDialog.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseInfo = response.body().string();
                Log.d("fan123", "onResponse: " + responseInfo);
                SharedPreferences.Editor editor = getSharedPreferences("userInfo", MODE_PRIVATE).edit();
                editor.putString("responseInfo", responseInfo);
                editor.commit();
                finish();
                mProgressDialog.dismiss();
            }
        });
    }

    public void okHttpPost(String url, String id) {
        //新建okhttp对象
        okHttpClient = new OkHttpClient();
        /**
         * 通过体传值
         */
        build = new FormBody.Builder()
                .add("wx_openid", id)
                .build();
        //创建request
        Request request = new Request.Builder()
                .url(url)
                .post(WXEntryActivity.build)
                .build();
        //回调方法
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                Log.d("onResponse", "onResponse: " + json);
                gson = new Gson();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wxLoginBean = gson.fromJson(json, WxLoginBean.class);
                        if ("200".equals(wxLoginBean.error_code) && wxLoginBean.data != null) {
                            if (wxLoginBean.data.type == 1) {
                                Intent intent = new Intent(WXEntryActivity.this, RegisterActivity.class);
                                startActivity(intent);
                            } else if (!TextUtils.isEmpty(wxLoginBean.data.token)) {
                                SharePreferenceHelper.getInstance(WXEntryActivity.this).put("token", wxLoginBean.data.token);
                                SharePreferenceHelper.getInstance(WXEntryActivity.this).put("is_verify", wxLoginBean.data.is_verify);
//                                SharePreferenceHelper.getInstance(WXEntryActivity.this).put("phone", phone);
                                Intent intent = new Intent(WXEntryActivity.this, MainActivity.class);
                                //intent.putExtra("phone",phone);
                                startActivity(intent);
                                finish();
                            }


                        }
                    }
                });


            }
        });
    }

}
