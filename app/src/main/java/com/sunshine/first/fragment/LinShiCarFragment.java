package com.sunshine.first.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.sunshine.first.BaseFragment;
import com.sunshine.first.R;
import com.sunshine.first.activity.StopCarPayTypeActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 临时车
 */
public class LinShiCarFragment extends BaseFragment {
    @BindView(R.id.icon_smallerror)
    ImageView iconSmallerror;
    @BindView(R.id.text_dingdan)
    TextView textDingdan;
    @BindView(R.id.icon_mydingdan)
    TextView iconMydingdan;
    @BindView(R.id.relative_phone_number)
    RelativeLayout relativePhoneNumber;
    @BindView(R.id.text_qchc)
    TextView textQchc;
    @BindView(R.id.icon_next)
    TextView iconNext;
    @BindView(R.id.relative_safe_password)
    RelativeLayout relativeSafePassword;
    @BindView(R.id.text_stop_time)
    TextView textStopTime;
    @BindView(R.id.text_stoptime)
    TextView textStoptime;
    @BindView(R.id.relative_stop_time)
    RelativeLayout relativeStopTime;
    @BindView(R.id.text_money)
    TextView textMoney;
    @BindView(R.id.text_linmoney)
    TextView textLinmoney;
    @BindView(R.id.relative_lin_money)
    RelativeLayout relativeLinMoney;
    @BindView(R.id.btn_right_pay)
    Button btnRightPay;
    @BindView(R.id.tv_car_number_inshicar)
    TextView tv_car_number_inshicar;


    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_linshicar;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
        btnRightPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), StopCarPayTypeActivity.class);
                startActivity(intent);
            }
        });


    }

    @OnClick({R.id.icon_smallerror, R.id.btn_right_pay,R.id.relative_phone_number,R.id.relative_safe_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_smallerror:
                tv_car_number_inshicar.setText("");
                break;
            case R.id.btn_right_pay://生成临时停车订单
                break;
            case R.id.relative_phone_number://生成临时停车订单
                TimePickerView pvTime=new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        iconMydingdan.setText((getTime(date)));

                    }
                }).setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                //.setTitleText("Title")//标题文字
//                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(true)//是否循环滚动
//                //.setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                //.setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
////                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
////                .setRangDate(startDate,endDate)//起始终止年月日设定
                        .setLabel("年", "月", "日", "时", "分", "秒")
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        //.isDialog(true)//是否显示为对话框样式
                        .build();

                pvTime.show();
                break;
            case R.id.relative_safe_password://生成临时停车订单
               TimePickerView pvTime1=new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        iconNext.setText((getTime(date)));

                    }
                }).setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                //.setTitleText("Title")//标题文字
//                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(true)//是否循环滚动
//                //.setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                //.setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
////                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
////                .setRangDate(startDate,endDate)//起始终止年月日设定
                        .setLabel("年", "月", "日", "时", "分", "秒")
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        //.isDialog(true)//是否显示为对话框样式
                        .build();

                pvTime1.show();
                break;
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

}
