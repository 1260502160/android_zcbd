package com.sunshine.first.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abner.ming.base.BaseFragment;
import com.abner.ming.base.model.Api;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.activity.CarInfoActivity;
import com.sunshine.first.activity.FeedbackActivity;
import com.sunshine.first.activity.HostmanRenActivity;
import com.sunshine.first.activity.MyIndentActivity;
import com.sunshine.first.activity.PayRecordActivity;
import com.sunshine.first.activity.PersonalActivity;
import com.sunshine.first.activity.RepairRecordActivity;
import com.sunshine.first.activity.VisitorRecordActivity;
import com.sunshine.first.activity.ZhuHuGuanLiActivity;
import com.sunshine.first.bean.MyInfomationBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends BaseFragment {

    @BindView(R.id.icon_zhuhuguanli)
    ImageView iconZhuhuguanli;
    @BindView(R.id.icon_carinfo)
    ImageView iconCarinfo;

    @BindView(R.id.icon_fangzhurenzheng)
    ImageView iconfangzhurenzheng;
    Unbinder unbinder;
    @BindView(R.id.rel_my_indent)
    RelativeLayout relmyindent;
    @BindView(R.id.rel_repair_record)
    RelativeLayout relrepairrecord;
    @BindView(R.id.rel_visitor_record)
    RelativeLayout relvisitorrecord;
    @BindView(R.id.rel_jiaofeijilu)
    RelativeLayout reljiaofeijilu;
    @BindView(R.id.rel_my_yijianfankui)
    RelativeLayout relMyYijianfankui;
    @BindView(R.id.rel_my_lianxikefu)
    RelativeLayout relMyLianxikefu;
    @BindView(R.id.relative_my_one)
    RelativeLayout relativemyone;
    @BindView(R.id.icon_my)
    ImageView iconMy;
    @BindView(R.id.text_admin)
    TextView textAdmin;
    @BindView(R.id.tv_sf)
    TextView tvSf;
    Unbinder unbinder1;
    private View inflate;
    private Intent intent;
    private PopupWindow pop;
    private Button btn_yes, btn_no;
    private String token;
    private Gson gson;
    private MyInfomationBean myInfomationBean;

    @Override
    protected void initData() {

        token = SharePreferenceHelper.getInstance(getContext()).getString("token", "");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        net(false, false).post(1, Api.GetUserInfo_URL, map);
        Log.i("bbb", "net");
    }

    @Override
    protected void initView(View view) {

        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.icon_zhuhuguanli, R.id.icon_carinfo, R.id.icon_fangzhurenzheng, R.id.rel_my_indent, R.id.rel_repair_record, R.id.rel_jiaofeijilu, R.id.rel_my_yijianfankui, R.id.rel_my_lianxikefu, R.id.relative_my_one, R.id.rel_visitor_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_zhuhuguanli:
                popwindow();
                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        intent = new Intent(getActivity(), HostmanRenActivity.class);
                        startActivity(intent);


                    }
                });
                /*intent = new Intent(getContext(), ZhuHuGuanLiActivity.class);
                startActivity(intent);*/
            case R.id.rel_visitor_record:
                intent = new Intent(getContext(), VisitorRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_carinfo:
                intent = new Intent(getActivity(), CarInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_fangzhurenzheng:
                intent = new Intent(getActivity(), HostmanRenActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_my_indent:
                intent = new Intent(getContext(), MyIndentActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_repair_record:
                intent = new Intent(getContext(), RepairRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_jiaofeijilu:
                intent = new Intent(getContext(), PayRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_my_yijianfankui:
                intent = new Intent(getContext(), FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_my_lianxikefu:
                break;
            case R.id.relative_my_one:
                intent = new Intent(getContext(), PersonalActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void popwindow() {

        View bottomView = View.inflate(getActivity(), R.layout.dialog_authenticated, null);
        btn_yes = bottomView.findViewById(R.id.btn_yes);
        btn_no = bottomView.findViewById(R.id.btn_no);
        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(false);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);

    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            gson = new Gson();
            myInfomationBean = gson.fromJson(data, MyInfomationBean.class);
            Glide.with(getContext()).load(myInfomationBean.getData().getPhoto());
            textAdmin.setText(myInfomationBean.getData().getNickname()+"");
            int is_verify = SharePreferenceHelper.getInstance(getContext()).getInt("is_verify", -1);
            if (is_verify==0){
                tvSf.setText("未认证");
            }else if (is_verify==1){
                tvSf.setText("已认证");
            }else if (is_verify==2){
                tvSf.setText("拒绝");
            }else if (is_verify==3){
                tvSf.setText("待审核");
            }
        }
    }

}
