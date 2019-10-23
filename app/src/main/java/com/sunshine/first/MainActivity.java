package com.sunshine.first;

import android.view.WindowManager;

import com.cafe.library.library.BottomTabInfo;
import com.cafe.library.library.BottomTabLayout;
import com.sunshine.first.fragment.HomeFragment;
import com.sunshine.first.fragment.MyFragment;
import com.sunshine.first.fragment.Serviceragment;
import com.sunshine.first.fragment.SmallPoliceFragment;

import java.util.ArrayList;

public class MainActivity extends BaseAppCompatActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        BottomTabLayout bottomTabLayout = (BottomTabLayout) findViewById(R.id.id_bottom_tab_layout);

        ArrayList<BottomTabInfo> bottomTabViews = new ArrayList<>();

        bottomTabViews.add(new BottomTabInfo(R.mipmap.home_grey, R.mipmap.home_blue, "首页", new HomeFragment()));
        bottomTabViews.add(new BottomTabInfo(R.mipmap.wuyegrey, R.mipmap.wuyeblue, "物业服务", new Serviceragment()));
        bottomTabViews.add(new BottomTabInfo(R.mipmap.homegrey, R.mipmap.homeblue, "家政服务", new SmallPoliceFragment()));
        bottomTabViews.add(new BottomTabInfo(R.mipmap.my_grey, R.mipmap.my_blue, "我的", new MyFragment()));
        bottomTabLayout.initData(bottomTabViews);
        bottomTabLayout.initFragment((getSupportFragmentManager()));


        //测试一下


        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}