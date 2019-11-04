package com.sunshine.first.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;

/**
 * 关于我们
 */
public class AboutUsActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_version_code)
    TextView tv_version_code;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        setDefaultTitle("关于我们");
        try {
            tv_version_code.setText("版本号  " + getVersionName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {

    }

    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

}
