package com.sunshine.first.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.abner.ming.base.model.Api;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.OnLineBean;
import com.sunshine.first.fragment.GoodsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * 在线商城
 */
public class OnlineStoreActivity extends BaseAppCompatActivity {
    @BindView(R.id.viewpager_store)
    ViewPager viewpagerStore;
    @BindView(R.id.tab_online_store)
    TabLayout tab_online_store;

    //写一个List集合，把每个页面，也就是Fragment,存进去
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<OnLineBean.OnLineDataBean> onLineBeanData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_online_store;
    }

    @Override
    protected void initView() {
        setDefaultTitle("在线商城");
    }

    @Override
    protected void initData() {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", getToken());
        net(true, false).post(1, Api.GetClaList_URL, stringMap);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (!TextUtils.isEmpty(data)) {
            if (type == 1) {
                OnLineBean onLineBean = gson.fromJson(data, OnLineBean.class);
                if (onLineBean != null) {
                    onLineBeanData = onLineBean.getData();
                    for (int i = 0; i < onLineBeanData.size(); i++) {
                        OnLineBean.OnLineDataBean onLineDataBean = onLineBeanData.get(i);
                        fragmentList.add(GoodsFragment.newInstance(onLineDataBean.getId() + "", onLineDataBean.getName()));
                    }
                    viewpagerStore.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                        @Override
                        public Fragment getItem(int i) {
                            return fragmentList.get(i);
                        }

                        @Override
                        public int getCount() {
                            return onLineBeanData == null ? 0 : onLineBeanData.size();
                        }

                        @Nullable
                        @Override
                        public CharSequence getPageTitle(int position) {
                            return onLineBeanData.get(position).getName();
                        }
                    });

                    tab_online_store.setupWithViewPager(viewpagerStore);
                    viewpagerStore.setCurrentItem(0);

                }
            }

        }
    }

}
