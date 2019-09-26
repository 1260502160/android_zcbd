package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunshine.first.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.relative_two)
    RelativeLayout relativeTwo;
    @BindView(R.id.relative_three)
    RelativeLayout relativeThree;
    @BindView(R.id.relative_four)
    RelativeLayout relativeFour;
    @BindView(R.id.relative_five)
    RelativeLayout relativeFive;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_personziliao);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.relative_two, R.id.relative_three, R.id.relative_four, R.id.relative_five})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.relative_two:
                break;
            case R.id.relative_three:
                break;
            case R.id.relative_four:
                break;
            case R.id.relative_five:
                intent = new Intent(PersonalActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
