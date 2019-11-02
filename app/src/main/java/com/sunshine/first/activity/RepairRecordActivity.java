package com.sunshine.first.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.fragment.AlreadyMoneyFragment;
import com.sunshine.first.fragment.RepairAllFragment;
import com.sunshine.first.fragment.WaitAgencyFragment;
import com.sunshine.first.fragment.WaitPayFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 报修记录
 */
public class RepairRecordActivity extends BaseAppCompatActivity {

    @BindView(R.id.tab_repair)
    TabLayout tabRepair;

    @BindView(R.id.viewpager_repair)
    ViewPager viewpagerRepair;

    //写一个List集合，把每个页面，也就是Fragment,存进去
    private List<Fragment> list;
    private ArrayList<String> title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_repair_records;
    }

    @Override
    protected void initView() {
        setDefaultTitle("报修记录");

    }

    @Override
    protected void initData() {
        title = new ArrayList<>();
        title.add("全部");
        title.add("待受理");
        title.add("代付款");
        title.add("已付款");
        //页面，数据源，里面是创建的三个页面（Fragment）
        list = new ArrayList<>();
        list.add(new RepairAllFragment());
        list.add(new WaitAgencyFragment());
        list.add(new WaitPayFragment());
        list.add(new AlreadyMoneyFragment());
        viewpagerRepair.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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

        tabRepair.setupWithViewPager(viewpagerRepair);
    }


}
