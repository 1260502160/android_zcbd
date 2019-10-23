package com.sunshine.first.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddHoueseHoldActivity extends BaseAppCompatActivity{
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.rel_name)
    RelativeLayout relName;
    @BindView(R.id.rel_sex)
    RelativeLayout relSex;
    @BindView(R.id.rel_phone)
    RelativeLayout relPhone;
    @BindView(R.id.rel_ID)
    RelativeLayout relID;
    @BindView(R.id.rel_relationship)
    RelativeLayout relRelationship;
    @BindView(R.id.rel_ID_number)
    RelativeLayout relIDNumber;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_family_identity;
    }

    @OnClick({R.id.icon_back, R.id.rel_name, R.id.rel_sex, R.id.rel_phone, R.id.rel_ID, R.id.rel_relationship, R.id.rel_ID_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.rel_name:
                break;
            case R.id.rel_sex:
                break;
            case R.id.rel_phone:
                break;
            case R.id.rel_ID:
                break;
            case R.id.rel_relationship:
                break;
            case R.id.rel_ID_number:
                break;
        }
    }
}
