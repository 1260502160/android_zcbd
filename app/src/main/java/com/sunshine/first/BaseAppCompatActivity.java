package com.sunshine.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.base.R;
import com.abner.ming.base.model.Api;
import com.abner.ming.base.mvp.model.BaseModelIml;
import com.abner.ming.base.mvp.presenter.BasePresenterIml;
import com.abner.ming.base.mvp.view.BaseView;
import com.abner.ming.base.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.activity.LoginActivity;
import com.sunshine.first.bean.ProvinceBean;
import com.sunshine.first.dialog.WheelDialog;
import com.sunshine.first.utils.SharePreferenceHelper;
import com.sunshine.first.utils.StatusBarUtil;
import com.sunshine.first.utils.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * author:AbnerMing
 * date:2019/4/18
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements BaseView {
    private RelativeLayout titleLayout;
    private TextView baseTitle, titleRight;
    private ImageView baseBack;
    private BasePresenterIml basePresenter;
    protected Gson gson = new Gson();
    protected Map<String, String> hashMap = new HashMap<>();
    protected int page = 1;
    protected int perpage = 10;
    final protected WheelDialog wheelDialog = new WheelDialog();
    protected List<ProvinceBean.ProvinceArrayBean> provinceArrayBeans1;
    protected List<ProvinceBean.ProvinceArrayBean> provinceArrayBeans2;
    protected List<ProvinceBean.ProvinceArrayBean> provinceArrayBeans3;

    /**
     * 设置标题
     */
    protected void setTitle(String title) {
        baseTitle.setText(title);
    }

    //是否显示返回键
    protected void isShowBack(boolean showBack) {
        if (showBack) {
            baseBack.setVisibility(View.VISIBLE);
        } else {
            baseBack.setVisibility(View.GONE);
        }
    }

    protected void setShowTitle(boolean isShow) {
        if (isShow) {
            titleLayout.setVisibility(View.GONE);
        } else {
            titleLayout.setVisibility(View.VISIBLE);
        }
    }

    //子类传递的一个layout
    public abstract int getLayoutId();

    //初始化View
    protected abstract void initView();

    //初始化View
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        titleLayout = (RelativeLayout) findViewById(R.id.base_layout_title);
        baseTitle = (TextView) findViewById(R.id.base_title);
        baseBack = (ImageView) findViewById(R.id.base_view_back);
        titleRight = (TextView) findViewById(R.id.base_title_right);

        //创建用于添加子类传递的布局
        FrameLayout baseView = (FrameLayout) findViewById(R.id.base_view);
        //拿到子类布局
        View childView = View.inflate(this, getLayoutId(), null);

        baseView.addView(childView);

        ButterKnife.bind(this);

        initView();

        initData();


        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    //获取BasePresenterIml  null为获取字符串，获取JavaBean需要传JavaBean
    public BasePresenterIml getPresenter(Class cls) {
        BaseModelIml baseModel = new BaseModelIml(cls);
        baseModel.isShowLoading(isShowLoading);
        baseModel.isReadCache(isReadCache);
        baseModel.setContext(this);
        baseModel.setHead(headMap);
        basePresenter = new BasePresenterIml(baseModel, this);
        return basePresenter;
    }

    //isShowLoading 是否显示加载框  isReadCache 是否阅读缓存  获取String
    protected BasePresenterIml net(boolean isShowLoading, boolean isReadCache) {
        return doHttp(null, isShowLoading, isReadCache);
    }

    //获取JavaBean
    protected BasePresenterIml net(boolean isShowLoading, boolean isReadCache, Class cls) {
        return doHttp(cls, isShowLoading, isReadCache);
    }

    protected BasePresenterIml doHttp(Class cls, boolean isShowLoading, boolean isReadCache) {
        isShowLoading(isShowLoading);
        isReadCache(isReadCache);
        return getPresenter(cls);
    }

    //是否读取缓存
    private boolean isReadCache;

    public void isReadCache(boolean isReadCache) {
        this.isReadCache = isReadCache;
    }

    //是否显示加载框
    private boolean isShowLoading;

    public void isShowLoading(boolean isShowLoading) {
        this.isShowLoading = isShowLoading;
    }

    private Map<String, String> headMap = new HashMap<>();

    public void setHead(Map<String, String> headMap) {
        this.headMap = headMap;
    }

    @Override
    public void success(int type, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            String error_code = jsonObject.optString("error_code");
            if ("403".equals(error_code)) {//重新登录
                SharePreferenceHelper.getInstance(this).put("token", "");
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else if (type == -1) {
                ToastManage.s(this, data);
            } else if (type == 110) {//获取省
                final ProvinceBean provinceBean = gson.fromJson(data, ProvinceBean.class);
                if (provinceBean != null && provinceBean.data != null && provinceBean.data.size() > 0) {
                    provinceArrayBeans1.clear();
                    provinceArrayBeans1.addAll(provinceBean.data);
                    wheelDialog.setData(provinceArrayBeans1, new WheelDialog.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(int position) {
                            ProvinceBean.ProvinceArrayBean provinceArrayBean = provinceBean.data.get(position);
                            if (provinceArrayBean != null) {
                                hashMap.put("id", provinceArrayBean.id + "");
                                net(false, false).post(111, Api.GetCityList_URL, hashMap);
                            }
                        }
                    }, new WheelDialog.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(int position) {
                            hashMap.put("id", provinceArrayBeans2.get(position).id + "");
                            net(false, false).post(112, Api.GetCityList_URL, hashMap);
                        }
                    }, new WheelDialog.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(int position) {

                        }
                    }, new WheelDialog.OnItemAllSelectedListener() {
                        @Override
                        public void onItemSelected(int position1, int position2, int position3) {//点击事件
                            try {
                                if (provinceArrayBeans1 != null && provinceArrayBeans2 != null && provinceArrayBeans3 != null) {
                                    ProvinceBean.ProvinceArrayBean provinceArrayBean1 = provinceArrayBeans1.get(position1);
                                    ProvinceBean.ProvinceArrayBean provinceArrayBean2 = provinceArrayBeans2.get(position2);
                                    ProvinceBean.ProvinceArrayBean provinceArrayBean3 = provinceArrayBeans3.get(position3);

                                    onSelectIdName.onSelectIdName(provinceArrayBean1.id, provinceArrayBean1.name, provinceArrayBean2.id,
                                            provinceArrayBean2.name, provinceArrayBean3.id, provinceArrayBean3.name);
                                }
                            } catch (Exception e) {
                                Logger.e("选择地址", "选择地址失败:" + e.toString());
                            }

                        }
                    }).show(getSupportFragmentManager(), "选择地址");
                }
            } else if (type == 111) {
                final ProvinceBean provinceBean = gson.fromJson(data, ProvinceBean.class);
                if (provinceBean != null && provinceBean.data != null && provinceBean.data.size() > 0) {
                    provinceArrayBeans2.clear();
                    provinceArrayBeans2.addAll(provinceBean.data);
                    wheelDialog.setList2(provinceArrayBeans2);

                }
            } else if (type == 112) {
                final ProvinceBean provinceBean = gson.fromJson(data, ProvinceBean.class);
                if (provinceBean != null && provinceBean.data != null && provinceBean.data.size() > 0) {
                    provinceArrayBeans3.clear();
                    provinceArrayBeans3.addAll(provinceBean.data);
                    wheelDialog.setList3(provinceArrayBeans3);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void successBean(int type, Object o) {

    }

    @Override
    public void fail(int type, String error) {

    }

    private SparseArray<View> sparseArray = new SparseArray<>();

    //用于获取控件的方法
    public View get(int id) {
        View view = sparseArray.get(id);
        if (view == null) {
            view = findViewById(id);
            sparseArray.put(id, view);
        }
        return view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (basePresenter != null) {
            basePresenter.destory();
        }
    }


    /**
     * 设置标题
     * 默认页面都这样展示特殊自己处理
     */
    protected void setDefaultTitle(String title) {
        baseTitle.setText(title);
        setShowTitle(false);
        isShowBack(true);
        StatusBarUtil.setImmersiveStatusBar(this, true);

//        //设置状态栏上的字体为黑色-因为本页面是白色必须设置
//        UtilsStyle.StatusBarLightMode(this,
//                RomUtils.getLightStatusBarAvailableRomType());
    }

    protected String getToken() {
        String token = SharePreferenceHelper.getInstance(this).getString("token", "");
        return TextUtils.isEmpty(token) ? "" : token;
    }

    /**
     * 三级联动
     */
    protected void showProvince(OnSelectIdName onSelectIdName) {
        this.onSelectIdName = onSelectIdName;
        provinceArrayBeans1 = new ArrayList<>();
        provinceArrayBeans2 = new ArrayList<>();
        provinceArrayBeans3 = new ArrayList<>();

        hashMap.put("id", "0");
        net(true, false).post(110, Api.GetCityList_URL, hashMap);

    }

    private OnSelectIdName onSelectIdName;

    public static interface OnSelectIdName {
        void onSelectIdName(int provinceId, String provinceName, int cityId, String cityName, int areaId, String areaName);
    }
}
