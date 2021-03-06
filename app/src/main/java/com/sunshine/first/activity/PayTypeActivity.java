package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sunshine.first.R;
import com.sunshine.first.fragment.AlreadyPayFragment;
import com.sunshine.first.fragment.FixCarFragment;
import com.sunshine.first.fragment.LinShiCarFragment;
import com.sunshine.first.fragment.NoPayFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayTypeActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.circle_recommend)
    RadioButton circleRecommend;
    @BindView(R.id.circle_attention)
    RadioButton circleAttention;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_type);
        ButterKnife.bind(this);
        //页面，数据源，里面是创建的三个页面（Fragment）
        list = new ArrayList<>();
        list.add(new NoPayFragment());
        list.add(new AlreadyPayFragment());
        circleFragmentViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        circleFragmentViewpager.setCurrentItem(0);
        onTabViewSelected(0  );
        circleRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.circle_recommend:
                        circleRecommend.setTextColor(getResources().getColor(R.color.black));
                        circleAttention.setTextColor(getResources().getColor(R.color.black));
                        circleFragmentViewpager.setCurrentItem(0);
                        onTabViewSelected(1);
                        break;

                    case R.id.circle_attention:
                        circleAttention.setTextColor(getResources().getColor(R.color.black));
                        circleRecommend.setTextColor(getResources().getColor(R.color.black));
                        circleFragmentViewpager.setCurrentItem(1);
                        onTabViewSelected(0);

                        break;
                }
            }
        });

    }

    @OnClick({R.id.icon_back, R.id.circle_recommend, R.id.circle_attention, R.id.circle_radiogroup, R.id.iv_line1, R.id.iv_line2, R.id.circle_fragment_viewpager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.circle_recommend:
                break;
            case R.id.circle_attention:
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
     * @param position
     * */
    private void onTabViewSelected(int position) {
        if(position<0 || position>3) {
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
}
