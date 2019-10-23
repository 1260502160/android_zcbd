package com.sunshine.first.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sunshine.first.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.R;
import com.sunshine.first.bean.GetResidentsListBean;
import com.sunshine.first.fragment.AuthenticatedFragment;
import com.sunshine.first.fragment.WaitApproveFragment;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;

/**
 * 住户认证
 */
public class HouseHoldIdentity extends BaseAppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.tab_household)
    TabLayout tabHousehold;
    @BindView(R.id.authenticated)
    RadioButton authenticated;
    @BindView(R.id.wait_approve)
    RadioButton waitApprove;
    @BindView(R.id.circle_radiogroup)
    RadioGroup circleRadiogroup;
    @BindView(R.id.iv_line1)
    ImageView ivLine1;
    @BindView(R.id.iv_line2)
    ImageView ivLine2;
    @BindView(R.id.circle_fragment_viewpager)
    ViewPager circleFragmentViewpager;
    //写一个List集合，把每个页面，也就是Fragment,存进去
    private List<Fragment> list;
    private ArrayList<String> title;

    @Override
    protected void initData() {
        title = new ArrayList<>();
        title.add("已认证");
        title.add("待审核");

        list = new ArrayList<>();
        list.add(new AuthenticatedFragment());
        list.add(new WaitApproveFragment());
        circleFragmentViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                return title.get(position);
            }
        });


        tabHousehold.setupWithViewPager(circleFragmentViewpager);


        circleFragmentViewpager.setCurrentItem(0);
        onTabViewSelected(0);
        circleRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.authenticated:
                        authenticated.setTextColor(getResources().getColor(R.color.blue));
                        waitApprove.setTextColor(getResources().getColor(R.color.black));
                        circleFragmentViewpager.setCurrentItem(0);
                        onTabViewSelected(0);
                        break;

                    case R.id.wait_approve:
                        waitApprove.setTextColor(getResources().getColor(R.color.blue));
                        authenticated.setTextColor(getResources().getColor(R.color.black));
                        circleFragmentViewpager.setCurrentItem(1);
                        onTabViewSelected(1);
                        break;
                }
            }
        });

        String token = SharePreferenceHelper.getInstance(this).getString("token", "");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("id", "1");
        map.put("status", "1");
        net(false, false).post(1, Api.GetResidentsList_URL, map);


    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_house_hold_identity;
    }

    @OnClick({R.id.icon_back, R.id.authenticated, R.id.wait_approve, R.id.circle_radiogroup, R.id.iv_line1, R.id.iv_line2, R.id.circle_fragment_viewpager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.authenticated:
                break;
            case R.id.wait_approve:
                break;
            case R.id.circle_radiogroup:
                break;
            case R.id.iv_line1:
                break;
            case R.id.iv_line2:
                break;
            case R.id.circle_fragment_viewpager:
                break;
        }
    }

    /**
     * 改变底栏图标选择状态
     *
     * @param position
     */
    private void onTabViewSelected(int position) {
        if (position < 0 || position > 3) {
            return;
        }

        ivLine1.setSelected(false);
        ivLine2.setSelected(false);

        switch (position) {
            case 0:
                ivLine1.setSelected(true);
                break;
            case 1:
                ivLine2.setSelected(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == -1) {
            ToastManage.s(this, data);
            return;
        } else if (type == 1) {
            if (!TextUtils.isEmpty(data)) {
                Gson gson = new Gson();
                GetResidentsListBean getResidentsListBean = gson.fromJson(data, GetResidentsListBean.class);
            }
        }

    }
}
