package com.sunshine.first.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abner.ming.base.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.bean.LouHaoBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;

public class HostmanRenActivity extends BaseAppCompatActivity{

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
    private Intent intent;
    private String id,name;
    private Gson gson;
    private LouHaoBean louHaoBean;
    private List<LouHaoBean.DataBean> louHaoBeanData;
    private String name1;


    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        tvChooseXiaoqu.setText(name);
        relZhurenzhengShenfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,String> map = new HashMap<>();
                map.put("type","2");
                map.put("id",id);
                net(false,false).post(1,Api.GetHosing_URL,map);
            }
        });
    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_hostman_ren;
    }

    @OnClick({R.id.icon_back, R.id.rel_xiaoqu, R.id.rel_zhurenzheng_shenfen, R.id.rel_zhurenzheng_louhao, R.id.rel_zhurenzheng_danyuanhao, R.id.rel_zhurenzheng_menpaihao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.rel_xiaoqu:
                intent = new Intent(HostmanRenActivity.this, ChooseCommityActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_zhurenzheng_shenfen:
                /*list = new ArrayList<>();
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
                picker.setSelectedTextColor(Color.BLUE);//前四位值是透明度
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
                picker.show();*/
                break;
            case R.id.rel_zhurenzheng_louhao:

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
                picker.setSelectedTextColor(Color.BLUE);//前四位值是透明度
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
                list.add("业主");
                list.add("租户");
                picker = new SinglePicker<>(HostmanRenActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("身份选择");
                picker.setSelectedIndex(8);
                picker.setWheelModeEnable(false);
                //启用权重 setWeightWidth 才起作用
                picker.setWeightEnable(true);
                picker.setWeightWidth(1);
                picker.setSelectedTextColor(Color.BLUE);//前四位值是透明度
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
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1) {
            gson = new Gson();
            louHaoBean = gson.fromJson(data, LouHaoBean.class);
            louHaoBeanData = louHaoBean.getData();
            for (LouHaoBean.DataBean louHaoBeanDatum : louHaoBeanData) {

                list = new ArrayList<>();
                list.add(louHaoBeanDatum.getName());
        }
                picker = new SinglePicker<>(HostmanRenActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("楼号选择");
                picker.setSelectedIndex(8);
                picker.setWheelModeEnable(false);
                //启用权重 setWeightWidth 才起作用
                picker.setWeightEnable(true);
                picker.setWeightWidth(1);
                picker.setSelectedTextColor(Color.BLUE);//前四位值是透明度
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

//            }



        }
    }
}
