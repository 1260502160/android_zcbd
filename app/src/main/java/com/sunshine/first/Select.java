package com.sunshine.first;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;

public class Select extends AppCompatActivity {

    private TextView tvChooseHobby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
//        tvChooseHobby = findViewById(R.id.tv_choose_hobby);
        ArrayList<String> list = new ArrayList<>();
        list.add("只限今天2019-09-12");
        list.add("只限明天2019-09-13");
        list.add("只限后天2019-09-14");
        SinglePicker<String> picker = new SinglePicker<>(Select.this,list);
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
                tvChooseHobby.setText(item);
            }
        });
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                tvChooseHobby.setText(item);
            }
        });
        picker.show();
    }
}
