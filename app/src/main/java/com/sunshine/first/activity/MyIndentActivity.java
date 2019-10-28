package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.fragment.AllFragment;
import com.sunshine.first.fragment.BackMoneyFragment;
import com.sunshine.first.fragment.FixCarFragment;
import com.sunshine.first.fragment.LinShiCarFragment;
import com.sunshine.first.fragment.PayMoneyFragment;
import com.sunshine.first.fragment.WaitPayFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyIndentActivity extends BaseAppCompatActivity{
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.tabs)
    TabLayout tabs;
   /* @BindView(R.id.all)
    RadioButton all;
    @BindView(R.id.wait_pay)
    RadioButton waitPay;
    @BindView(R.id.already_pay)
    RadioButton alreadyPay;
    @BindView(R.id.back_money)
    RadioButton backMoney;
    @BindView(R.id.circle_radiogroup)
    RadioGroup circleRadiogroup;*/
    @BindView(R.id.iv_line1)
    ImageView ivLine1;
    @BindView(R.id.iv_line2)
    ImageView ivLine2;
    @BindView(R.id.iv_line3)
    ImageView ivLine3;
    @BindView(R.id.iv_line4)
    ImageView ivLine4;
    @BindView(R.id.viewpager_indent)
    ViewPager viewpagerIndent;

    //写一个List集合，把每个页面，也就是Fragment,存进去
    private List<Fragment> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_indent;
    }

    @Override
    protected void initView() {
        /*//页面，数据源，里面是创建的三个页面（Fragment）
        list = new ArrayList<>();
        list.add(new AllFragment());
        list.add(new WaitPayFragment());
        list.add(new PayMoneyFragment());
        list.add(new BackMoneyFragment());
        viewpagerIndent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewpagerIndent.setCurrentItem(0);
        onTabViewSelected(0  );
        circleRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.all:
                        all.setTextColor(getResources().getColor(R.color.blue));
                        waitPay.setTextColor(getResources().getColor(R.color.black));
                        alreadyPay.setTextColor(getResources().getColor(R.color.black));
                        backMoney.setTextColor(getResources().getColor(R.color.black));
                        viewpagerIndent.setCurrentItem(0);
                        onTabViewSelected(0);
                        break;

                    case R.id.wait_pay:
                        waitPay.setTextColor(getResources().getColor(R.color.blue));
                        alreadyPay.setTextColor(getResources().getColor(R.color.black));
                        backMoney.setTextColor(getResources().getColor(R.color.black));
                        all.setTextColor(getResources().getColor(R.color.black));
                        viewpagerIndent.setCurrentItem(1);
                        onTabViewSelected(1);
                        break;
                    case R.id.already_pay:
                        alreadyPay.setTextColor(getResources().getColor(R.color.blue));
                        waitPay.setTextColor(getResources().getColor(R.color.black));
                        backMoney.setTextColor(getResources().getColor(R.color.black));
                        all.setTextColor(getResources().getColor(R.color.black));
                        viewpagerIndent.setCurrentItem(2);
                        onTabViewSelected(2);
                        break;
                    case R.id.back_money:
                        backMoney.setTextColor(getResources().getColor(R.color.blue));
                        waitPay.setTextColor(getResources().getColor(R.color.black));
                        backMoney.setTextColor(getResources().getColor(R.color.black));
                        all.setTextColor(getResources().getColor(R.color.black));
                        viewpagerIndent.setCurrentItem(3);
                        onTabViewSelected(3);
                        break;
                }
            }
        });*/
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


    @OnClick({R.id.icon_back, R.id.all, R.id.wait_pay, R.id.already_pay, R.id.back_money, R.id.circle_radiogroup, R.id.iv_line1, R.id.iv_line2, R.id.iv_line3, R.id.iv_line4, R.id.viewpager_indent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.all:
                break;
            case R.id.wait_pay:
                break;
            case R.id.already_pay:
                break;
            case R.id.back_money:
                break;
            case R.id.circle_radiogroup:
                break;
            case R.id.iv_line1:
                break;
            case R.id.iv_line2:
                break;
            case R.id.iv_line3:
                break;
            case R.id.iv_line4:
                break;
            case R.id.viewpager_indent:
                break;
        }
    }

    /**
     * 改变底栏图标选择状态
     * @param position
     * */
    private void onTabViewSelected(int position) {
        if(position<0 || position>3) {
            return;
        }

        ivLine1.setSelected(false);
        ivLine2.setSelected(false);
        ivLine3.setSelected(false);
        ivLine4.setSelected(false);

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
            default:
                break;
        }
    }
}
