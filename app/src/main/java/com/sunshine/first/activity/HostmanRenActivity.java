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
                ArrayList<String> list = new ArrayList<>();
                list.add("只限今天2019-09-12");
                list.add("只限明天2019-09-13");
                list.add("只限后天2019-09-14");
                SinglePicker<String> picker = new SinglePicker<>(HostmanRenActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
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
                        tvChooseXiaoqu.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvChooseXiaoqu.setText(item);
                    }
                });
                picker.show();
                break;
            case R.id.rel_zhurenzheng_shenfen:
                break;
            case R.id.rel_zhurenzheng_louhao:
                break;
            case R.id.rel_zhurenzheng_danyuanhao:
                break;
            case R.id.rel_zhurenzheng_menpaihao:
                break;
        }
    }


}
