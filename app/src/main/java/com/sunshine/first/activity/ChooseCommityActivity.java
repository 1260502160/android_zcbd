package com.sunshine.first.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.abner.ming.base.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.adapter.ChooseCummintyAdapter;
import com.sunshine.first.adapter.StoreAllAdapter;
import com.sunshine.first.bean.GetCommunityBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseCommityActivity extends BaseAppCompatActivity {


    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.s_main_spinner)
    Spinner s_main_spinner ;
    @BindView(R.id.s_main_spinnerTwo)
    Spinner s_main_spinnerTwo;
    @BindView(R.id.icon_search)
    ImageView iconSearch;
    @BindView(R.id.edit_hosingname)
    EditText editHosingname;
    @BindView(R.id.recycle_choose_city)
    RecyclerView recycleChooseCity;
    private Gson gson;
    private GetCommunityBean getCommunityBean;
    private List<GetCommunityBean.DataBean> getCommunityBeanData;
    private LinearLayoutManager linearLayoutManager;
    private ChooseCummintyAdapter chooseCummintyAdapter;
    private String hosingname;
    private HashMap<String, String> map;

    @Override
    protected void initData() {


        //设置省份
        final String provinces[]={"湖南省","河南省","海南省"};
        //给省份设置图标
        int images[]={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        //添加市
        final Map<String,String[]> citys=new HashMap<String, String[]>();
        citys.put("湖南省",new String[]{"长沙市","衡阳市","益阳市"});
        citys.put("河南省",new String[]{"郑州市","许昌市","周口市"});
        citys.put("海南省",new String[]{"海口市","三亚市","文昌市"});

        //将图标添加进集合
        List<Map<String,Object>> list=new ArrayList<>();
        for (int i = 0; i < provinces.length; i++) {
            Map<String,Object> map=new HashMap<>();
            map.put("title",provinces[i]);
            map.put("image",images[i]);
            list.add(map);
        }


        //创建布局管理器
        linearLayoutManager = new LinearLayoutManager(ChooseCommityActivity.this);
        //设置布局管理器
        recycleChooseCity.setLayoutManager(linearLayoutManager);
        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        chooseCummintyAdapter = new ChooseCummintyAdapter(ChooseCommityActivity.this);
        //设置Adapter
        recycleChooseCity.setAdapter(chooseCummintyAdapter);
        chooseCummintyAdapter.setOnItemClick(new ChooseCummintyAdapter.onClick() {
            @Override
            public void OnItemClick(View view, int pos) {
                Intent intent = new Intent(ChooseCommityActivity.this, HostmanRenActivity.class);
                intent.putExtra("ss",getCommunityBeanData.get(pos));

                startActivity(intent);
            }
        });
        map = new HashMap<>();
        map.put("type","1");
        net(false,false).post(1,Api.GetHosing_URL,map);
        iconSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hosingname = editHosingname.getText().toString();

                map.put("keyword",hosingname);
                net(false,false).post(1,Api.GetHosing_URL,map);
    }
});

    }


    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_choose_hosing;
    }


    @OnClick({R.id.icon_back, R.id.recycle_choose_city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.recycle_choose_city:
                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){
            gson = new Gson();
            getCommunityBean = gson.fromJson(data, GetCommunityBean.class);
            getCommunityBeanData = getCommunityBean.getData();
            if (getCommunityBeanData!=null){
                chooseCummintyAdapter.setDataList(getCommunityBeanData);
            }


        }

    }

/*
    //适配器
    SimpleAdapter adapterSpinner=new SimpleAdapter(this,list,android.R.layout.activity_list_item,new String[]{"title","image"},new int[]{android.R.id.text1,android.R.id.icon});
    //上面适配器引用的是系统布局，所以需要按照系统布局的方式来排列
        s_main_spinner.setAdapter(adapterSpinner);

    //给下拉列表设置选择事件
        s_main_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //先根据下标拿到省份，再根据拿到的省份去集合中拿到市
            String city[]=citys.get(provinces[position]);
            //将拿到的市添加到适配器中
            ArrayAdapter ada=new ArrayAdapter(ChooseCommityActivity.this,android.R.layout.simple_list_item_1,city);
            s_main_spinnerTwo.setAdapter(ada);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });*/
}



