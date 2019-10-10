package com.sunshine.first.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.abner.ming.base.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VisitorRegistrationActivity extends BaseAppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    private View iconback;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        iconback = get(R.id.icon_back);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_visitor_registration;
    }


}
