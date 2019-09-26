package com.sunshine.first.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.sunshine.first.R;
import com.sunshine.first.activity.VisitorInvitationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class EmpowerFragment extends Fragment {
    @BindView(R.id.btn_share_link)
    Button btnShareLink;
    @BindView(R.id.btn_again_invation)
    Button btnAgainInvation;
    Unbinder unbinder;
    private PopupWindow pop;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adapter_visitor, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_share_link, R.id.btn_again_invation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_share_link:
                popwindow();
                break;
            case R.id.btn_again_invation:
                intent = new Intent(getContext(), VisitorInvitationActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void popwindow() {

        View bottomView = View.inflate(getContext(), R.layout.dialog_visitor_link, null);
        ImageView rq = bottomView.findViewById(R.id.icon_rq);
        ImageView error = bottomView.findViewById(R.id.icon_error);
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
}
