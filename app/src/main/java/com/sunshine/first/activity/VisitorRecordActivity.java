package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sunshine.first.R;
import com.sunshine.first.fragment.AuthenticatedFragment;
import com.sunshine.first.fragment.EmpowerFragment;
import com.sunshine.first.fragment.GetGoFragment;
import com.sunshine.first.fragment.WaitApproveFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VisitorRecordActivity extends AppCompatActivity {

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.empower)
    RadioButton empower;
    @BindView(R.id.get_go)
    RadioButton getGo;
    @BindView(R.id.radiogroup_visitor)
    RadioGroup radiogroupVisitor;
    @BindView(R.id.iv_line1)
    ImageView ivLine1;
    @BindView(R.id.iv_line2)
    ImageView ivLine2;
    @BindView(R.id.viewpager_visitor)
    ViewPager viewpagerVisitor;
    //写一个List集合，把每个页面，也就是Fragment,存进去
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_record);
        ButterKnife.bind(this);
        //页面，数据源，里面是创建的三个页面（Fragment）
        list = new ArrayList<>();
        list.add(new EmpowerFragment());
        list.add(new GetGoFragment());
        viewpagerVisitor.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewpagerVisitor.setCurrentItem(0);
        onTabViewSelected(0  );
        radiogroupVisitor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.empower:
                        empower.setTextColor(getResources().getColor(R.color.blue));
                        getGo.setTextColor(getResources().getColor(R.color.black));
                        viewpagerVisitor.setCurrentItem(0);
                        onTabViewSelected(0);
                        break;

                    case R.id.get_go:
                        getGo.setTextColor(getResources().getColor(R.color.blue));
                        empower.setTextColor(getResources().getColor(R.color.black));
                        viewpagerVisitor.setCurrentItem(1);
                        onTabViewSelected(1);
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
