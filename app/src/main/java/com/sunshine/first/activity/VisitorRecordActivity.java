package com.sunshine.first.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.abner.ming.base.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.R;
import com.sunshine.first.adapter.StoreAllAdapter;
import com.sunshine.first.adapter.VisitorRecoderAdapter;
import com.sunshine.first.bean.VisitorRecodBean;
import com.sunshine.first.fragment.AuthenticatedFragment;
import com.sunshine.first.fragment.EmpowerFragment;
import com.sunshine.first.fragment.GetGoFragment;
import com.sunshine.first.fragment.WaitApproveFragment;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VisitorRecordActivity extends BaseAppCompatActivity{

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.recycle_visitor_record)
    RecyclerView recycleVisitorRecord;
    private VisitorRecoderAdapter visitorRecoderAdapter;


    @Override
    protected void initData() {


        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VisitorRecordActivity.this);
        //设置布局管理器
        recycleVisitorRecord.setLayoutManager(linearLayoutManager);
        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        visitorRecoderAdapter = new VisitorRecoderAdapter(VisitorRecordActivity.this);
        //设置Adapter
        recycleVisitorRecord.setAdapter(visitorRecoderAdapter);

        String token = SharePreferenceHelper.getInstance(VisitorRecordActivity.this).getString("token", "");
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        net(false,false).post(1,Api.VisitorRecord_URL,map);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


    }

    @Override
    protected void initView() {

        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_visitor_record;
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==1){
            Gson gson = new Gson();
            VisitorRecodBean visitorRecodBean = gson.fromJson(data, VisitorRecodBean.class);
            List<VisitorRecodBean.DataBean.ListBean> list = visitorRecodBean.getData().getList();
            if (list!=null){

                visitorRecoderAdapter.setDataList(list);

            }
        }
    }
}
