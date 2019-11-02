package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubmitSuccActivity extends BaseAppCompatActivity {
    @BindView(R.id.btn_lookdeail)
    Button btnLookdeail;
    @BindView(R.id.btn_homeback)
    Button btnHomeback;

    @Override
    protected void initData() {
        btnLookdeail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubmitSuccActivity.this, BaoLookDeatilActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setDefaultTitle("提交成功");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repair_success;
    }


    @OnClick({R.id.btn_lookdeail, R.id.btn_homeback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_lookdeail:
                break;
            case R.id.btn_homeback:
                 finish();
                break;
        }
    }
}
