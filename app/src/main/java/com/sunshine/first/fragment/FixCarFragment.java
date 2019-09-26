package com.sunshine.first.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunshine.first.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;

public class FixCarFragment extends Fragment {


    @BindView(R.id.text_carnumber)
    TextView textCarnumber;
    @BindView(R.id.text_cycle)
    TextView textcycle;
    @BindView(R.id.icon_cycle)
    ImageView iconCycle;
    @BindView(R.id.btn_right_pay)
    Button btnRightPay;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixcar, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.text_carnumber, R.id.icon_cycle, R.id.btn_right_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_carnumber:
                break;
            case R.id.icon_cycle:
                ArrayList<String> list = new ArrayList<>();
                list.add("月");
                list.add("季");
                list.add("年");
                list.add("半年");

//        String[] ss = (String[]) list.toArray();
                SinglePicker<String> picker = new SinglePicker<>(getActivity(),list);
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
                        textcycle.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        textcycle.setText(item);
                    }
                });
                picker.show();
                break;
            case R.id.btn_right_pay:
                break;
        }
    }
}
