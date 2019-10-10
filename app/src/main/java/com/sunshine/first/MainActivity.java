package com.sunshine.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cafe.library.library.BottomTabInfo;
import com.cafe.library.library.BottomTabLayout;
import com.sunshine.first.fragment.HomeFragment;
import com.sunshine.first.fragment.MyFragment;
import com.sunshine.first.fragment.SmallPoliceFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomTabLayout bottomTabLayout = (BottomTabLayout) findViewById(R.id.id_bottom_tab_layout);

        ArrayList<BottomTabInfo> bottomTabViews = new ArrayList<>();

        bottomTabViews.add(new BottomTabInfo(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, "首页", new HomeFragment()));
        bottomTabViews.add(new BottomTabInfo(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, "微警务", new SmallPoliceFragment()));
        bottomTabViews.add(new BottomTabInfo(R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, "我的", new MyFragment()));


        bottomTabLayout.initData(bottomTabViews);
        bottomTabLayout.initFragment((getSupportFragmentManager()));

    }
}