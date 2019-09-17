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

import com.sunshine.first.R;
import com.sunshine.first.activity.BaoXiuActivity;
import com.sunshine.first.activity.FangKeDengJiActivity;
import com.sunshine.first.activity.YeZhuRenZhengActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Author：wtt
 * @E-mail： 15001194794@163.com
 * @Date：2019/3/5 19:27
 * @Description：描述信息
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.icon_yezhurenzheng)
    ImageView iconYezhurenzheng;
    @BindView(R.id.icon_shequstore)
    ImageView iconShequstore;
    @BindView(R.id.icon_fangkedengji)
    ImageView iconFangkedengji;
    @BindView(R.id.icon_baoxiu)
    ImageView iconBaoxiu;
    @BindView(R.id.icon_bianminjiaofei)
    ImageView iconBianminjiaofei;
    Unbinder unbinder;
    private View view;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.icon_yezhurenzheng, R.id.icon_shequstore, R.id.icon_fangkedengji, R.id.icon_baoxiu, R.id.icon_bianminjiaofei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_yezhurenzheng:
                intent = new Intent(getContext(), YeZhuRenZhengActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_shequstore:
                break;
            case R.id.icon_fangkedengji:
                intent = new Intent(getContext(), FangKeDengJiActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_baoxiu:
                intent = new Intent(getContext(), BaoXiuActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_bianminjiaofei:
                break;
        }
    }
}
