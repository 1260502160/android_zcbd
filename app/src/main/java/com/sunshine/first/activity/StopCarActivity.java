package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.fragment.FixCarFragment;
import com.sunshine.first.fragment.LinShiCarFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
停车费
 */
public class StopCarActivity extends BaseAppCompatActivity {
    @BindView(R.id.tab_stopcar)
    TabLayout tabStopcar;
    @BindView(R.id.circle_fragment_viewpager)
    ViewPager circleFragmentViewpager;

    //写一个List集合，把每个页面，也就是Fragment,存进去
    private List<Fragment> list;
    private List<String> title;


    @Override
    public int getLayoutId() {
        return R.layout.activity_stop_car;
    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);
        setDefaultTitle("停车费");
    }

    @Override
    protected void initData() {
        title = new ArrayList<>();
        title.add("固定车位");
        title.add("临时停车");
        list = new ArrayList<>();
        list.add(new FixCarFragment());
        list.add(new LinShiCarFragment());
        circleFragmentViewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
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
        tabStopcar.setupWithViewPager(circleFragmentViewpager);

    }


}

