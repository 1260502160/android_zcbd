package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.fragment.AllFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 我的订单页面
 */
public class MyIndentActivity extends BaseAppCompatActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager_indent)
    ViewPager viewpagerIndent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_indent;
    }

    @Override
    protected void initView() {
        setDefaultTitle("我的订单");

    }

    @Override
    protected void initData() {
        final ArrayList<String> title = new ArrayList<>();
        title.add("全部");
        title.add("代付款");
        title.add("已付款");
        title.add("退款");
        final ArrayList<Fragment> fragment = new ArrayList<>();

        for (int i = 0; i < title.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("status", i);
            AllFragment allFragment = new AllFragment();
            allFragment.setArguments(bundle);
            fragment.add(allFragment);
        }
        viewpagerIndent.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragment.get(position);
            }

            @Override
            public int getCount() {
                return fragment.size();
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
        tabs.setupWithViewPager(viewpagerIndent);
    }

}
