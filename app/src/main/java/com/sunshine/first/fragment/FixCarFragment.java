package com.sunshine.first.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.activity.StopCarPayTypeActivity;
import com.sunshine.first.bean.GetCarListBean;
import com.sunshine.first.bean.ParkingChargeBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * 固定车位
 */
public class FixCarFragment extends BaseFragment {


    @BindView(R.id.text_car_number)
    TextView textCarnumber;
    @BindView(R.id.re_cycle)
    RelativeLayout reCycle;

    @BindView(R.id.text_cycle)
    TextView textcycle;
    @BindView(R.id.icon_cycle)
    ImageView iconCycle;
    @BindView(R.id.btn_right_pay)
    Button btnRightPay;
    Unbinder unbinder;
    private String carnumber;
    private String cycle;
    private int car_id, car_pay_type;
    private String plate_num;
    private int comm_id;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fixcar;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
        comm_id = getActivity().getIntent().getIntExtra("comm_id", 0);
        carnumber = textCarnumber.getText().toString();
        cycle = textcycle.getText().toString();

    }

    @OnClick({R.id.text_carnumber, R.id.re_cycle, R.id.btn_right_pay, R.id.rl_car_fixcar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_car_fixcar://选择车辆
                hashMap.clear();
                hashMap.put("token", getToken());
                net(true, false).post(10, Api.GetCarList_URL, hashMap);
                break;
            case R.id.text_carnumber:
                break;
            case R.id.re_cycle:
                ArrayList<String> list = new ArrayList<>();
                list.add("月");
                list.add("季");
                list.add("年");
//                list.add("半年");

//        String[] ss = (String[]) list.toArray();
                SinglePicker<String> picker = new SinglePicker<>(getActivity(), list);
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
//                        textcycle.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        textcycle.setText(item);
                        if ("月".equals(item)) {
                            car_pay_type = 1;
                        } else if ("季".equals(item)) {
                            car_pay_type = 2;
                        } else {
                            car_pay_type = 3;
                        }
                    }
                });
                picker.show();
                break;
            case R.id.btn_right_pay://生成订单
                hashMap.clear();
                hashMap.put("token", getToken());
                hashMap.put("car_id", car_id + "");//车辆ID
                hashMap.put("comm_id", comm_id + "");//社区ID
                hashMap.put("car_pay_type", car_pay_type + "");//选择时间：1.月付，2.季付，3.年付
                hashMap.put("plate_num", plate_num);//车牌号

                net(true, false).post(11, Api.ParkingCharge_URL, hashMap);

                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (10 == type) {//获取车辆列表
            GetCarListBean getCarListBean = gson.fromJson(data, GetCarListBean.class);
            if (getCarListBean != null && getCarListBean.getData() != null && getCarListBean.getData().size() > 0) {


//        String[] ss = (String[]) list.toArray();
                SinglePicker<GetCarListBean.DataBean> picker = new SinglePicker<>(getActivity(), getCarListBean.getData());
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
                picker.setOnItemPickListener(new OnItemPickListener<GetCarListBean.DataBean>() {

                    @Override
                    public void onItemPicked(int i, GetCarListBean.DataBean dataBean) {
                        car_id = dataBean.getId();
                        plate_num = dataBean.getCommunity_name() + "";
                        textCarnumber.setText(plate_num);
                    }

                });
                picker.show();

            }


        }
        if (11 == type) {
            ParkingChargeBean parkingChargeBean = gson.fromJson(data, ParkingChargeBean.class);
            if (parkingChargeBean != null && parkingChargeBean.getData() != null) {
                Intent intent = new Intent(getContext(), StopCarPayTypeActivity.class);
                intent.putExtra("id", parkingChargeBean.getData().getId());
                intent.putExtra("plate_num", parkingChargeBean.getData().getPlate_num());
                intent.putExtra("pay_money", parkingChargeBean.getData().getPay_money());
                intent.putExtra("type", parkingChargeBean.getData().getType());
                startActivity(intent);
            }
        }

    }
}
