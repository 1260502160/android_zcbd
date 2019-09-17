package com.sunshine.first.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sunshine.first.R;
import com.sunshine.first.activity.BaoXiuJiLuActivity;
import com.sunshine.first.activity.FangKeJiLuActivity;
import com.sunshine.first.activity.HostmanRenActivity;
import com.sunshine.first.activity.JiaoFeiJiLuActivity;
import com.sunshine.first.activity.PersonalActivity;
import com.sunshine.first.activity.ZhuHuGuanLiActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends Fragment {

    @BindView(R.id.icon_zhuhuguanli)
    ImageView iconZhuhuguanli;
    @BindView(R.id.icon_cheliangxingxi)
    ImageView iconCheliangxingxi;
    @BindView(R.id.icon_menjindengji)
    ImageView iconMenjindengji;
    @BindView(R.id.icon_fangzhurenzheng)
    ImageView iconfangzhurenzheng;
    Unbinder unbinder;
    @BindView(R.id.rel_my_fangkejilu)
    RelativeLayout relMyFangkejilu;
    @BindView(R.id.rel_my_baoxiujilu)
    RelativeLayout relMyBaoxiujilu;
    @BindView(R.id.rel_my_jiaofeijilu)
    RelativeLayout relMyJiaofeijilu;
    @BindView(R.id.rel_my_yijianfankui)
    RelativeLayout relMyYijianfankui;
    @BindView(R.id.rel_my_lianxikefu)
    RelativeLayout relMyLianxikefu;
    @BindView(R.id.relative_my_one)
    RelativeLayout relativemyone;
    private View inflate;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.icon_zhuhuguanli, R.id.icon_cheliangxingxi, R.id.icon_menjindengji, R.id.icon_fangzhurenzheng,R.id.rel_my_fangkejilu, R.id.rel_my_baoxiujilu, R.id.rel_my_jiaofeijilu, R.id.rel_my_yijianfankui, R.id.rel_my_lianxikefu,R.id.relative_my_one})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_zhuhuguanli:
                intent = new Intent(getContext(), ZhuHuGuanLiActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_cheliangxingxi:
                break;
            case R.id.icon_menjindengji:
                break;
            case R.id.icon_fangzhurenzheng:
                intent = new Intent(getContext(), HostmanRenActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_my_fangkejilu:
                intent= new Intent(getContext(), FangKeJiLuActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_my_baoxiujilu:
                intent = new Intent(getContext(), BaoXiuJiLuActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_my_jiaofeijilu:
                intent = new Intent(getContext(), JiaoFeiJiLuActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_my_yijianfankui:
                break;
            case R.id.rel_my_lianxikefu:
                break;
            case R.id.relative_my_one:
                intent = new Intent(getContext(), PersonalActivity.class);
                startActivity(intent);
                break;
        }
    }


}
