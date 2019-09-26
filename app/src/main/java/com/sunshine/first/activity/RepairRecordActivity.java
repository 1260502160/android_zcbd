package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sunshine.first.R;
import com.sunshine.first.fragment.AllFragment;
import com.sunshine.first.fragment.AlreadyMoneyFragment;
import com.sunshine.first.fragment.BackMoneyFragment;
import com.sunshine.first.fragment.PayMoneyFragment;
import com.sunshine.first.fragment.RepairAllFragment;
import com.sunshine.first.fragment.WaitAgencyFragment;
import com.sunshine.first.fragment.WaitPayFragment;
import com.sunshine.first.fragment.WaitPayMoneyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepairRecordActivity extends AppCompatActivity {
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.repair_all)
    RadioButton repairAll;
    @BindView(R.id.wait_agency)
    RadioButton waitAgency;
    @BindView(R.id.wait_pay)
    RadioButton waitPay;
    @BindView(R.id.already_pay)
    RadioButton alreadyPay;
    @BindView(R.id.circle_radiogroup)
    RadioGroup circleRadiogroup;
    @BindView(R.id.iv_line1)
    ImageView ivLine1;
    @BindView(R.id.iv_line2)
    ImageView ivLine2;
    @BindView(R.id.iv_line3)
    ImageView ivLine3;
    @BindView(R.id.iv_line4)
    ImageView ivLine4;
    @BindView(R.id.viewpager_repair)
    ViewPager viewpagerRepair;

    //写一个List集合，把每个页面，也就是Fragment,存进去
    private List<Fragment> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_records);
        ButterKnife.bind(this);
        //页面，数据源，里面是创建的三个页面（Fragment）
        list = new ArrayList<>();
        list.add(new RepairAllFragment());
        list.add(new WaitAgencyFragment());
        list.add(new WaitPayMoneyFragment());
        list.add(new AlreadyMoneyFragment());
        viewpagerRepair.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewpagerRepair.setCurrentItem(0);
        onTabViewSelected(0  );
        circleRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.repair_all:
                        repairAll.setTextColor(getResources().getColor(R.color.blue));
                        waitAgency.setTextColor(getResources().getColor(R.color.black));
                        alreadyPay.setTextColor(getResources().getColor(R.color.black));
                        waitPay.setTextColor(getResources().getColor(R.color.black));
                        viewpagerRepair.setCurrentItem(0);
                        onTabViewSelected(0);
                        break;

                    case R.id.wait_agency:
                        waitAgency.setTextColor(getResources().getColor(R.color.blue));
                        repairAll.setTextColor(getResources().getColor(R.color.black));
                        alreadyPay.setTextColor(getResources().getColor(R.color.black));
                        waitPay.setTextColor(getResources().getColor(R.color.black));
                        viewpagerRepair.setCurrentItem(1);
                        onTabViewSelected(1);
                        break;
                    case R.id.wait_pay:
                        waitPay.setTextColor(getResources().getColor(R.color.blue));
                        repairAll.setTextColor(getResources().getColor(R.color.black));
                        alreadyPay.setTextColor(getResources().getColor(R.color.black));
                        waitAgency.setTextColor(getResources().getColor(R.color.black));
                        viewpagerRepair.setCurrentItem(2);
                        onTabViewSelected(2);
                        break;
                    case R.id.already_pay:
                        alreadyPay.setTextColor(getResources().getColor(R.color.blue));
                        repairAll.setTextColor(getResources().getColor(R.color.black));
                        waitPay.setTextColor(getResources().getColor(R.color.black));
                        waitAgency.setTextColor(getResources().getColor(R.color.black));
                        viewpagerRepair.setCurrentItem(3);
                        onTabViewSelected(3);
                        break;
                }
            }
        });
    }

    @OnClick(R.id.icon_back)
    public void onViewClicked() {
        finish();
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
