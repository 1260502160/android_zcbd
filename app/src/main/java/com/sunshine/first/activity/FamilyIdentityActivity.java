package com.sunshine.first.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunshine.first.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;

public class FamilyIdentityActivity extends AppCompatActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.rel_name)
    RelativeLayout relName;
    @BindView(R.id.rel_sex)
    RelativeLayout relSex;
    @BindView(R.id.rel_phone)
    RelativeLayout relPhone;
    @BindView(R.id.rel_relationship)
    RelativeLayout relRelationship;
    @BindView(R.id.rel_ID_number)
    RelativeLayout relIDNumber;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_choose_sex)
    TextView tvChooseSex;
    @BindView(R.id.tv_ID_number)
    TextView tvIDNumber;
    private ArrayList<String> list;
    private SinglePicker<String> picker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_family_identity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.rel_name, R.id.rel_sex, R.id.rel_phone, R.id.rel_relationship, R.id.rel_ID_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.rel_name:
                break;
            case R.id.rel_sex:
                ArrayList<String> list = new ArrayList<>();
                list.add("男");
                list.add("女");
                SinglePicker<String> picker = new SinglePicker<>(FamilyIdentityActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("性别");
                picker.setSelectedIndex(8);
                picker.setWheelModeEnable(false);
                //启用权重 setWeightWidth 才起作用
                picker.setWeightEnable(true);
                picker.setWeightWidth(1);
                picker.setSelectedTextColor(Color.BLACK);//前四位值是透明度
                picker.setUnSelectedTextColor(Color.GRAY);
                picker.setOnSingleWheelListener(new OnSingleWheelListener() {
                    @Override
                    public void onWheeled(int index, String item) {
                        tvSex.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvSex.setText(item);
                    }
                });
                picker.show();
                break;
            case R.id.rel_phone:
                break;
            case R.id.rel_relationship:
                list = new ArrayList<>();
                list.add("业主");
                list.add("亲友");
                list.add("家属");
                list.add("其他");
                picker = new SinglePicker<>(FamilyIdentityActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("关系选择");
                picker.setSelectedIndex(8);
                picker.setWheelModeEnable(false);
                //启用权重 setWeightWidth 才起作用
                picker.setWeightEnable(true);
                picker.setWeightWidth(1);
                picker.setSelectedTextColor(Color.BLACK);//前四位值是透明度
                picker.setUnSelectedTextColor(Color.GRAY);
                picker.setOnSingleWheelListener(new OnSingleWheelListener() {
                    @Override
                    public void onWheeled(int index, String item) {
                        tvSex.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvSex.setText(item);
                    }
                });
                picker.show();
                break;
            case R.id.rel_ID_number:
                break;
        }
    }
}
