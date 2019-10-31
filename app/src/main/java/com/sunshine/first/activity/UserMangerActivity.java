package com.sunshine.first.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.adapter.UserManagerAdapter;
import com.sunshine.first.bean.HouseListBean;
import com.sunshine.first.bean.ShowOwnerVerifyBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserMangerActivity extends BaseAppCompatActivity {

    @BindView(R.id.recycle_usemanger)
    RecyclerView recycleUsemanger;
    @BindView(R.id.btn_add_infomation)
    Button btnAddInfomation;
    private UserManagerAdapter userManagerAdapter;
    private HouseListBean houseListBean;
    private String token;

    @Override
    protected void initData() {

        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserMangerActivity.this);
        //设置布局管理器
        recycleUsemanger.setLayoutManager(linearLayoutManager);
        //设置为垂直布局，这也是默认的
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        userManagerAdapter = new UserManagerAdapter(UserMangerActivity.this);

        //设置Adapter
        recycleUsemanger.setAdapter(userManagerAdapter);

        token = SharePreferenceHelper.getInstance(UserMangerActivity.this).getString("token", "");
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("type", "1");
        net(false, false).post(1, Api.HousesList_URL, map);

        btnAddInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserMangerActivity.this, FamilyIdentityActivity.class);
                intent.putExtra("relationship", "房主");
                startActivity(intent);
            }
        });
        setDefaultTitle("房屋列表");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_usermanger;
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {

            Gson gson = new Gson();
            houseListBean = gson.fromJson(data, HouseListBean.class);
            if (houseListBean.getData() != null && houseListBean.getData().getList() != null && houseListBean.getData().getList().size() > 0) {
                userManagerAdapter.setData(houseListBean.getData().getList());
            }
        }

        if (type == 2) {

            Gson gson = new Gson();
            ShowOwnerVerifyBean showOwnerVerifyBean = gson.fromJson(data, ShowOwnerVerifyBean.class);
            Toast.makeText(UserMangerActivity.this, showOwnerVerifyBean.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 在list的Item点击事件中传入item的索引  通过索引获取到当天item对应的user的id 然后请求接口执行删除操作
     */
    public void deleUserInfo(int index) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("id", houseListBean.getData().getList().get(index).getId() + "");
        net(false, false).post(2, Api.Del_Residents, map);
    }
}
