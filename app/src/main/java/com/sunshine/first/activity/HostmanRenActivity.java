package com.sunshine.first.activity;

import android.graphics.Color;
import android.os.Bundle;
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

public class HostmanRenActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.rel_xiaoqu)
    RelativeLayout relxiaoqu;
    @BindView(R.id.rel_zhurenzheng_shenfen)
    RelativeLayout relZhurenzhengShenfen;
    @BindView(R.id.rel_zhurenzheng_louhao)
    RelativeLayout relZhurenzhengLouhao;
    @BindView(R.id.rel_zhurenzheng_danyuanhao)
    RelativeLayout relZhurenzhengDanyuanhao;
    @BindView(R.id.rel_zhurenzheng_menpaihao)
    RelativeLayout relZhurenzhengMenpaihao;
    @BindView(R.id.tv_choose_xiaoqu)
    TextView tvChooseXiaoqu;
    @BindView(R.id.text_shenfen)
    TextView textShenfen;
    @BindView(R.id.text_louhao)
    TextView textLouhao;
    @BindView(R.id.text_danyuanhao)
    TextView textDanyuanhao;
    @BindView(R.id.text_menpaihao)
    TextView textMenpaihao;
    private ArrayList<String> list;
    private SinglePicker<String> picker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostman_ren);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.icon_back, R.id.rel_xiaoqu, R.id.rel_zhurenzheng_shenfen, R.id.rel_zhurenzheng_louhao, R.id.rel_zhurenzheng_danyuanhao, R.id.rel_zhurenzheng_menpaihao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.rel_xiaoqu:
                break;
            case R.id.rel_zhurenzheng_shenfen:
                ArrayList<String> list = new ArrayList<>();
                list.add("业主");
                list.add("家人");
                list.add("租户");
                SinglePicker<String> picker = new SinglePicker<>(HostmanRenActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("身份选择");
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
                        textShenfen.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        textShenfen.setText(item);
                    }
                });
                picker.show();
                break;
            case R.id.rel_zhurenzheng_louhao:
                list = new ArrayList<>();
                list.add("113#");
                list.add("114#");
                list.add("115#");
                list.add("116#");
                picker = new SinglePicker<>(HostmanRenActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("楼栋选择");
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
                        textLouhao.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        textLouhao.setText(item);
                    }
                });
                picker.show();
                break;
            case R.id.rel_zhurenzheng_danyuanhao:
                list = new ArrayList<>();
                list.add("1单元");
                list.add("2单元");
                list.add("3单元");
                list.add("4单元");
                list.add("5单元");
                picker = new SinglePicker<>(HostmanRenActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("单元选择");
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
                        textDanyuanhao.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        textDanyuanhao.setText(item);
                    }
                });
                picker.show();
                break;
            case R.id.rel_zhurenzheng_menpaihao:
                list = new ArrayList<>();
                list.add("101");
                list.add("102");
                list.add("103");
                list.add("104");
                picker = new SinglePicker<>(HostmanRenActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("房屋选择");
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
                        textMenpaihao.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        textMenpaihao.setText(item);
                    }
                });
                picker.show();
                break;
        }
    }


}
