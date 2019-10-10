package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sunshine.first.R;
import com.sunshine.first.fragment.AlreadyMoneyFragment;
import com.sunshine.first.fragment.EntrieFragment;
import com.sunshine.first.fragment.GriftFragment;
import com.sunshine.first.fragment.HouseHoldFragment;
import com.sunshine.first.fragment.OilFragment;
import com.sunshine.first.fragment.RepairAllFragment;
import com.sunshine.first.fragment.StoreAllFragment;
import com.sunshine.first.fragment.WaitAgencyFragment;
import com.sunshine.first.fragment.WaitPayMoneyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnlineStoreActivity extends AppCompatActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.store_all)
    RadioButton storeAll;
    @BindView(R.id.entrie)
    RadioButton entrie;
    @BindView(R.id.household)
    RadioButton household;
    @BindView(R.id.oil)
    RadioButton oil;
    @BindView(R.id.grift)
    RadioButton grift;
    @BindView(R.id.store_radiogroup)
    RadioGroup storeRadiogroup;
    @BindView(R.id.iv_line1)
    ImageView ivLine1;
    @BindView(R.id.iv_line2)
    ImageView ivLine2;
    @BindView(R.id.iv_line3)
    ImageView ivLine3;
    @BindView(R.id.iv_line4)
    ImageView ivLine4;
    @BindView(R.id.iv_line5)
    ImageView ivLine5;
    @BindView(R.id.viewpager_store)
    ViewPager viewpagerStore;

    //写一个List集合，把每个页面，也就是Fragment,存进去
    private List<Fragment> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_store);
        ButterKnife.bind(this);
        //页面，数据源，里面是创建的三个页面（Fragment）
        list = new ArrayList<>();
        list.add(new StoreAllFragment());
        list.add(new EntrieFragment());
        list.add(new HouseHoldFragment());
        list.add(new OilFragment());
        list.add(new GriftFragment());
        viewpagerStore.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        viewpagerStore.setCurrentItem(0);
        onTabViewSelected(0  );
        storeRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.store_all:
                        storeAll.setTextColor(getResources().getColor(R.color.blue));
                        entrie.setTextColor(getResources().getColor(R.color.black));
                        household.setTextColor(getResources().getColor(R.color.black));
                        oil.setTextColor(getResources().getColor(R.color.black));
                        grift.setTextColor(getResources().getColor(R.color.black));
                        viewpagerStore.setCurrentItem(0);
                        onTabViewSelected(0);
                        break;

                    case R.id.entrie:
                        entrie.setTextColor(getResources().getColor(R.color.blue));
                        storeAll.setTextColor(getResources().getColor(R.color.black));
                        household.setTextColor(getResources().getColor(R.color.black));
                        oil.setTextColor(getResources().getColor(R.color.black));
                        grift.setTextColor(getResources().getColor(R.color.black));
                        viewpagerStore.setCurrentItem(1);
                        onTabViewSelected(1);
                        break;
                    case R.id.household:
                        household.setTextColor(getResources().getColor(R.color.blue));
                        storeAll.setTextColor(getResources().getColor(R.color.black));
                        entrie.setTextColor(getResources().getColor(R.color.black));
                        oil.setTextColor(getResources().getColor(R.color.black));
                        grift.setTextColor(getResources().getColor(R.color.black));
                        viewpagerStore.setCurrentItem(2);
                        onTabViewSelected(2);
                        break;
                    case R.id.oil:
                        oil.setTextColor(getResources().getColor(R.color.blue));
                        storeAll.setTextColor(getResources().getColor(R.color.black));
                        entrie.setTextColor(getResources().getColor(R.color.black));
                        household.setTextColor(getResources().getColor(R.color.black));
                        grift.setTextColor(getResources().getColor(R.color.black));
                        viewpagerStore.setCurrentItem(3);
                        onTabViewSelected(3);
                        break;
                    case R.id.grift:
                        grift.setTextColor(getResources().getColor(R.color.blue));
                        storeAll.setTextColor(getResources().getColor(R.color.black));
                        entrie.setTextColor(getResources().getColor(R.color.black));
                        household.setTextColor(getResources().getColor(R.color.black));
                        oil.setTextColor(getResources().getColor(R.color.black));
                        viewpagerStore.setCurrentItem(4);
                        onTabViewSelected(4);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.icon_back, R.id.store_all, R.id.entrie, R.id.household, R.id.oil, R.id.grift, R.id.store_radiogroup, R.id.iv_line1, R.id.iv_line2, R.id.iv_line3, R.id.iv_line4, R.id.viewpager_store, R.id.iv_line5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.store_all:
                break;
            case R.id.entrie:
                break;
            case R.id.household:
                break;
            case R.id.oil:
                break;
            case R.id.grift:
                break;
            case R.id.store_radiogroup:
                break;
            case R.id.iv_line1:
                break;
            case R.id.iv_line2:
                break;
            case R.id.iv_line3:
                break;
            case R.id.iv_line4:
                break;
            case R.id.iv_line5:
                break;
            case R.id.viewpager_store:
                break;
        }
    }

    /**
     * 改变底栏图标选择状态
     * @param position
     * */
    private void onTabViewSelected(int position) {
        if(position<0 || position>4) {
            return;
        }

        ivLine1.setSelected(false);
        ivLine2.setSelected(false);
        ivLine3.setSelected(false);
        ivLine4.setSelected(false);
        ivLine5.setSelected(false);

        switch (position) {
            case 0:
                ivLine1.setSelected(true);
                break;
            case 1:
                ivLine2.setSelected(true);
                break;
            case 2:
                ivLine3.setSelected(true);
                break;
            case 3:
                ivLine4.setSelected(true);
                break;
            case 4:
                ivLine5.setSelected(true);
                break;
            default:
                break;
        }
    }
}
