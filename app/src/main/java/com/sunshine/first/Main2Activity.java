package com.sunshine.first;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.net.URISyntaxException;

public class Main2Activity extends AppCompatActivity {
    private static final String URL_FORMAT =
            "intent://platformapi/startapp?saId=10000007&" +
                    "clientVersion=3.7.0.0718&qrcode=https%3A%2F%2Fqr.alipay.com%2F{urlCode}%3F_s" +
                    "%3Dweb-other&_t=1472443966571#Intent;" + "scheme=alipayqr;package=com.eg.android.AlipayGphone;end";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.main2_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlipay(Main2Activity.this, URL_FORMAT);
            }
        });
    }

    public boolean startAlipay(Activity activity, String urlCode) {
        return startIntentUrl(activity, URL_FORMAT.replace("{urlCode}", urlCode));
    }

    public boolean startIntentUrl(Activity activity, String intentUrl) {
        try {
            Intent intent = Intent.parseUri(intentUrl, Intent.URI_INTENT_SCHEME);
            activity.startActivity(intent);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
