package com.sunshine.first.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunshine.first.R;


/**
 * @Author：wtt
 * @E-mail： 15001194794@163.com
 * @Date：2019/3/5 19:27
 * @Description：描述信息
 */
public class SmallPoliceFragment extends Fragment{

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_smallpolice, container, false);
        return view;
    }
}
