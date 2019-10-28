package com.sunshine.first.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sunshine.first.R;
import com.sunshine.first.fragment.FixCarFragment;
import com.sunshine.first.fragment.LinShiCarFragment;

import java.util.ArrayList;
import java.util.List;
/*
停车费
 */
public class StopCarActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ImageView iconback,iv_line1,iv_line2;
    private RadioGroup radiogroup;
    private RadioButton circle_recommend,circle_attention;

    //写一个List集合，把每个页面，也就是Fragment,存进去
    private List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_car);
        //实例化
        viewPager = (ViewPager) findViewById(R.id.circle_fragment_viewpager);
        iconback = (ImageView) findViewById(R.id.icon_back);
        radiogroup = (RadioGroup) findViewById(R.id.circle_radiogroup);

        circle_recommend = findViewById(R.id.circle_recommend);
        circle_attention = findViewById(R.id.circle_attention);
        iv_line1=findViewById(R.id.iv_line1);
        iv_line2=findViewById(R.id.iv_line2);
        iconback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //页面，数据源，里面是创建的三个页面（Fragment）
        list = new ArrayList<>();
        list.add(new FixCarFragment());
        list.add(new LinShiCarFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.setCurrentItem(0);
        onTabViewSelected(0  );
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.circle_recommend:
                        circle_recommend.setTextColor(getResources().getColor(R.color.black));
                        circle_attention.setTextColor(getResources().getColor(R.color.transparent));
                        viewPager.setCurrentItem(0);
                        onTabViewSelected(0);
                        break;

                    case R.id.circle_attention:
                            circle_attention.setTextColor(getResources().getColor(R.color.colorAccent));
                            circle_recommend.setTextColor(getResources().getColor(R.color.colorPrimary));
                            viewPager.setCurrentItem(1);
                            onTabViewSelected(1);

                        break;
                }
            }
        });
    }

    /**
     * 改变底栏图标选择状态
     * @param position
     * */
    private void onTabViewSelected(int position) {
        if(position<0 || position>3) {
            return;
        }

        iv_line1.setSelected(false);
        iv_line2.setSelected(false);

        switch (position) {
            case 0:
                iv_line1.setSelected(true);
                break;
            case 1:
                iv_line2.setSelected(true);
                break;
            default:
                break;
        }
    }


}

