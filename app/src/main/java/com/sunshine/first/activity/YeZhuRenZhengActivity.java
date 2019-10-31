package com.sunshine.first.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 业主认证页面
 */
public class YeZhuRenZhengActivity extends BaseAppCompatActivity {


    @BindView(R.id.relative_fzrz)
    RelativeLayout relativeFzrz;
    @BindView(R.id.zhgl)
    RelativeLayout zhgl;
    @BindView(R.id.clrz)
    RelativeLayout clrz;


    @Override
    public int getLayoutId() {
        return R.layout.layout_yezhurenzheng;
    }

    @Override
    protected void initView() {
        setDefaultTitle("业主认证");

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.relative_fzrz, R.id.zhgl, R.id.clrz})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.relative_fzrz://房主认证

                intent = new Intent(YeZhuRenZhengActivity.this, HostmanRenActivity.class);
                startActivity(intent);
                break;
            case R.id.zhgl://住户管理

                intent = new Intent(YeZhuRenZhengActivity.this, UserMangerActivity.class);
                startActivity(intent);
                break;
            case R.id.clrz://车辆认证

                intent = new Intent(YeZhuRenZhengActivity.this, CarInfoActivity.class);
                startActivity(intent);
                break;

        }
    }
}
