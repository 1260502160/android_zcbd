package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YeZhuRenZhengActivity extends AppCompatActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.relative_fzrz)
    RelativeLayout relativeFzrz;
    @BindView(R.id.zhgl)
    RelativeLayout zhgl;
    @BindView(R.id.clrz)
    RelativeLayout clrz;
    @BindView(R.id.mjrz)
    RelativeLayout mjrz;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_yezhurenzheng);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.relative_fzrz, R.id.zhgl, R.id.clrz, R.id.mjrz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.relative_fzrz:
                break;
            case R.id.zhgl:
                break;
            case R.id.clrz:
                break;
            case R.id.mjrz:
                break;
        }
    }
}
