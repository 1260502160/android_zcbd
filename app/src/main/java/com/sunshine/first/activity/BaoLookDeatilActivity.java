package com.sunshine.first.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.base.model.Api;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.adapter.RepairAllAdapter;
import com.sunshine.first.bean.CancelRepairBean;
import com.sunshine.first.bean.ReapirDeatilBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 报修订单详情
 */
public class BaoLookDeatilActivity extends BaseAppCompatActivity {

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.tv_repari_descrip)
    TextView tvRepariDescrip;
    @BindView(R.id.icon_repair)
    ImageView iconRepair;
    @BindView(R.id.tv_repair_address)
    TextView tvRepairAddress;
    @BindView(R.id.text_reapir_time)
    TextView textReapirTime;
    @BindView(R.id.tv_drscrip_name)
    TextView tvDrscripName;
    @BindView(R.id.tv_descrip_phone)
    TextView tvDescripPhone;

    @Override
    protected void initData() {

        int id = getIntent().getIntExtra("id", -1);
        String token = SharePreferenceHelper.getInstance(BaoLookDeatilActivity.this).getString("token", "");
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("id",id+"");
        net(false, false).post(1, Api.GetRepairDetail_URL, map);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SharePreferenceHelper.getInstance(BaoLookDeatilActivity.this).getString("token", "");
                HashMap<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("id", "1");
                net(false, false).post(2, Api.CancelRepair_URL, map);
            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setDefaultTitle("报修订单详情");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repairs_detail;
    }

    @OnClick({R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:

                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==-1){
            ToastManage.s(BaoLookDeatilActivity.this,data);
        }else if (type == 1) {
            Gson gson = new Gson();
            ReapirDeatilBean reapirDeatilBean = gson.fromJson(data, ReapirDeatilBean.class);
            tvRepariDescrip.setText(reapirDeatilBean.getData().getExplain());
            tvDrscripName.setText(reapirDeatilBean.getData().getName());
            tvDescripPhone.setText(reapirDeatilBean.getData().getMobile());
            tvRepairAddress.setText(reapirDeatilBean.getData().getHouses_number_name());
            textReapirTime.setText(reapirDeatilBean.getData().getRepair_time());
            Glide.with(BaoLookDeatilActivity.this).load(reapirDeatilBean.getData().getImgs()).into(iconRepair);
        }else if (type == 2){
            Gson gson = new Gson();
            CancelRepairBean cancelRepairBean = gson.fromJson(data, CancelRepairBean.class);
            if ("200".equals(cancelRepairBean.getError_code())){
                finish();
            }
            Toast.makeText(BaoLookDeatilActivity.this,cancelRepairBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }


}
