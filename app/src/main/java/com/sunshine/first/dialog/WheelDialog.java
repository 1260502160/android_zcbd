package com.sunshine.first.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sunshine.first.R;
import com.sunshine.first.bean.ProvinceBean;
import com.sunshine.first.view.WheelAdapter;
import com.sunshine.first.view.WheelView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wavewave
 * @CreateDate: 2019/3/18 2:45 PM
 * @Description:
 * @Version: 1.0
 */
public class WheelDialog extends DialogFragment implements View.OnClickListener {

    private List<ProvinceBean.ProvinceArrayBean> list1 = new ArrayList<>();
    private List<ProvinceBean.ProvinceArrayBean> list2 = new ArrayList<>();
    private List<ProvinceBean.ProvinceArrayBean> list3 = new ArrayList<>();
    private OnItemSelectedListener onItemSelectedListener1;
    private OnItemSelectedListener onItemSelectedListener2;
    private OnItemSelectedListener onItemSelectedListener3;
    private OnItemAllSelectedListener onItemAllSelectedListener;
    private int position1 = 0;
    private int position2 = 0;
    private int position3 = 0;
    private WheelView wheelView;
    private WheelView wheelView2;
    private WheelView wheelView3;


    /**
     * 设置数据
     *
     * @param
     * @return
     */
    public WheelDialog setData(List<ProvinceBean.ProvinceArrayBean> list1, OnItemSelectedListener onItemSelectedListener1,
                               OnItemSelectedListener onItemSelectedListener2, OnItemSelectedListener onItemSelectedListener3,
                               OnItemAllSelectedListener onItemAllSelectedListener) {
        this.onItemSelectedListener1 = onItemSelectedListener1;
        this.onItemSelectedListener2 = onItemSelectedListener2;
        this.onItemSelectedListener3 = onItemSelectedListener3;
        this.onItemAllSelectedListener = onItemAllSelectedListener;
        this.list1.addAll(list1);
        return this;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        getDialog().setCanceledOnTouchOutside(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = 0f;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_wheel, null);
        initView(view);

        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        return view;
    }

    private void initView(View view) {

        TextView dialog_wheel_cancel_tv = view.findViewById(R.id.dialog_wheel_cancel_tv);
        TextView dialog_wheel_ok_tv = view.findViewById(R.id.dialog_wheel_ok_tv);
        dialog_wheel_cancel_tv.setOnClickListener(this);
        dialog_wheel_ok_tv.setOnClickListener(this);

        wheelView = view.findViewById(R.id.wheelView1);
        wheelView2 = view.findViewById(R.id.wheelView2);
        wheelView3 = view.findViewById(R.id.wheelView3);

        setAdapterListener(wheelView, list1, onItemSelectedListener1, 1);
        setAdapterListener(wheelView2, list2, onItemSelectedListener2, 2);
        setAdapterListener(wheelView3, list3, onItemSelectedListener3, 3);

        if (list1 != null && list1.size() > 0)
            onItemSelectedListener1.onItemSelected(0);
    }

    public void setList2(List<ProvinceBean.ProvinceArrayBean> list2) {
        this.list2 = list2;
        setAdapterListener(wheelView2, list2, onItemSelectedListener2, 2);
        onItemSelectedListener2.onItemSelected(0);
    }

    public void setList3(List<ProvinceBean.ProvinceArrayBean> list3) {
        this.list3 = list3;
        setAdapterListener(wheelView3, list3, onItemSelectedListener3, 3);
        onItemSelectedListener3.onItemSelected(0);
    }


    private void setAdapterListener(WheelView wheelView, final List<ProvinceBean.ProvinceArrayBean> list1, final OnItemSelectedListener onItemSelectedListener, final int type) {
        if (type == 3) {
            position3 = 0;
        } else if (type == 2) {
            position2 = 0;
        } else {
            position2 = 0;
        }
        wheelView.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return list1.size();
            }

            @Override
            public Object getItem(int index) {
                return list1.get(index).name;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }
        });
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                if (type == 3) {
                    position3 = position;
                } else if (type == 2) {
                    position2 = position;
                } else {
                    position1 = position;
                }
                onItemSelectedListener.onItemSelected(position);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_wheel_cancel_tv:
                dismiss();
                break;
            case R.id.dialog_wheel_ok_tv:
                onItemAllSelectedListener.onItemSelected(position1, position2, position3);
//                if (onItemSelectedListener1 != null)
//                    onItemSelectedListener1.onItemSelected(position1);
//
//                if (onItemSelectedListener2 != null)
//                    onItemSelectedListener2.onItemSelected(position2);
//
//                if (onItemSelectedListener3 != null)
//                    onItemSelectedListener3.onItemSelected(position3);

                dismiss();
                break;
        }
    }

    public static interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    public static interface OnItemAllSelectedListener {
        void onItemSelected(int position1, int position2, int position3);
    }
}
