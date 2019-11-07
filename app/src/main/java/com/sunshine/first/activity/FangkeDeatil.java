package com.sunshine.first.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.GetResidentsDetailBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 访客详情
 */
public class FangkeDeatil extends BaseAppCompatActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_idnumber)
    TextView tvIdnumber;
    @BindView(R.id.icon_head)
    ImageView iconHead;
    @BindView(R.id.icon_china)
    ImageView iconChina;
    @BindView(R.id.icon_home_zm)
    ImageView iconHomeZm;
    private GetResidentsDetailBean.DataBean getResidentsDetailBeanData;
    private int sex;

    @Override
    public int getLayoutId() {
        return R.layout.activity_household_info;
    }

    @Override
    protected void initView() {
        setDefaultTitle("住户信息");
    }

    @Override
    protected void initData() {
        String token = SharePreferenceHelper.getInstance(FangkeDeatil.this).getString("token", "");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        int id = getIntent().getIntExtra("id", -1);
        map.put("id", id + "");
        net(false, false).post(1, Api.GetResidentsDetail_URL, map);

    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            Gson gson = new Gson();
            GetResidentsDetailBean getResidentsDetailBean = gson.fromJson(data, GetResidentsDetailBean.class);
            getResidentsDetailBeanData = getResidentsDetailBean.getData();
            tvName.setText(getResidentsDetailBeanData.getResidents_name());
            tvSex.setText(getResidentsDetailBeanData.getSex());
            sex = getResidentsDetailBeanData.getSex();
            if (sex == 0) {
                tvSex.setText("男");
            } else if (sex == 1) {
                tvSex.setText("女");
            }
            tvPhoneNumber.setText(getResidentsDetailBeanData.getResidents_mobile() + "");
            tvIdnumber.setText(getResidentsDetailBeanData.getIdentity_card_number() + "");

        }
    }
}
