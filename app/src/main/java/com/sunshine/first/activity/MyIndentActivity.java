package com.sunshine.first.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.fragment.AllFragment;
import com.sunshine.first.fragment.BackMoneyFragment;
import com.sunshine.first.fragment.PayMoneyFragment;
import com.sunshine.first.fragment.WaitPayFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyIndentActivity extends BaseAppCompatActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
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
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        final ArrayList<String> title = new ArrayList<>();
        title.add("全部");
        title.add("代付款");
        title.add("已付款");
        title.add("退款");
        final ArrayList<Fragment> fragment = new ArrayList<>();
        fragment.add(new AllFragment());
        fragment.add(new WaitPayFragment());
        fragment.add(new PayMoneyFragment());
        fragment.add(new BackMoneyFragment());
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


    @OnClick({R.id.icon_back, R.id.viewpager_indent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.viewpager_indent:
                break;
        }
    }
}
