package com.sunshine.first.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abner.ming.base.BaseAppCompatActivity;
import com.bigkoo.pickerview.TimePickerView;
import com.sunshine.first.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;

public class VisitorRegistrationActivity extends BaseAppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.tv_visitor_record)
    TextView tvVisitorRecord;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.recycle_visitor_registration)
    RecyclerView recycleVisitorRegistration;
    @BindView(R.id.relative_visitor_time)
    RelativeLayout relativeVisitorTime;
    @BindView(R.id.relative_visitor_name)
    RelativeLayout relativeVisitorName;
    @BindView(R.id.relative_visitor_phonenumber)
    RelativeLayout relativeVisitorPhonenumber;
    @BindView(R.id.relative_take_photo)
    RelativeLayout relativeTakePhoto;
    @BindView(R.id.btn_submit_visitor)
    Button btnSubmitVisitor;
    private View iconback;

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);

        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_visitor_registration;
    }


    @OnClick({R.id.icon_back, R.id.tv_visitor_record, R.id.relative_visitor_time, R.id.relative_visitor_name, R.id.relative_visitor_phonenumber, R.id.relative_take_photo, R.id.btn_submit_visitor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                break;
            case R.id.tv_visitor_record:
                break;
            case R.id.relative_visitor_time:
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tvName.setText((getTime(date)));

                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
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
                        .setLabel("年","月","日","时","分","秒")
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        //.isDialog(true)//是否显示为对话框样式
                        .build();

                pvTime.show();
                break;
            case R.id.relative_visitor_name:
                break;
            case R.id.relative_visitor_phonenumber:
                break;
            case R.id.relative_take_photo:
                break;
            case R.id.btn_submit_visitor:
                break;
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

}
