package com.sunshine.first.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.HousenumberBean;
import com.sunshine.first.bean.LouCengBean;
import com.sunshine.first.bean.LouHaoBean;
import com.sunshine.first.bean.OwnerVerifyBean;
import com.sunshine.first.bean.UnitNumber;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * 房主认证
 */
public class HostmanRenActivity extends BaseAppCompatActivity {

    @BindView(R.id.rel_xiaoqu)
    RelativeLayout relxiaoqu;
    @BindView(R.id.rel_zhurenzheng_shenfen)
    RelativeLayout relZhurenzhengShenfen;
    @BindView(R.id.rel_zhurenzheng_louhao)
    RelativeLayout relZhurenzhengLouhao;
    @BindView(R.id.rel_zhurenzheng_danyuanhao)
    RelativeLayout relZhurenzhengDanyuanhao;
    @BindView(R.id.rel_zhurenzheng_menpaihao)
    RelativeLayout relZhurenzhengMenpaihao;
    @BindView(R.id.relative_louceng)
    RelativeLayout relativeLouceng;
    @BindView(R.id.tv_choose_xiaoqu)
    TextView tvChooseXiaoqu;
    @BindView(R.id.text_shenfen)
    TextView textShenfen;
    @BindView(R.id.text_louhao)
    TextView textLouhao;
    @BindView(R.id.text_danyuanhao)
    TextView textDanyuanhao;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.text_louCengId)
    TextView textlouCengId;
    @BindView(R.id.text_menpaihao)
    TextView textMenpaihao;
    private ArrayList<String> list;
    private SinglePicker<String> picker, picker1, picker2, picker3, picker4;
    private Intent intent;
    private int xiaoquId = -1;
    private Gson gson;
    private LouHaoBean louHaoBean;
    private List<LouHaoBean.DataBean> louHaoBeanData;
    private UnitNumber unitNumber;
    private List<UnitNumber.DataBean> unitNumberData;
    private HousenumberBean housenumberBean;
    private List<HousenumberBean.DataBean> housenumberBeanData;

    private int louId = -1, danyuanId = -1, menId = -1, loucengId = -1;
    private List<LouCengBean.DataBean> louCengBeanData;
    private String shenfen;
    private int TO_CHOOSE_COMMITY_ACTIVITY_REQUEST_CODE = 10001;


    @Override
    public int getLayoutId() {
        return R.layout.activity_hostman_ren;
    }

    @Override
    protected void initView() {

        setDefaultTitle("房主认证");
    }

    @Override
    protected void initData() {
        //楼号
        relZhurenzhengShenfen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, String> map = new HashMap<>();
                map.put("type", 2 + "");
                if (xiaoquId != -1)
                    map.put("id", xiaoquId + "");
                else {
                    Toast.makeText(HostmanRenActivity.this, "请先选择小区", Toast.LENGTH_SHORT).show();
                    return;
                }
                net(false, false).post(1, Api.GetHosing_URL, map);

            }
        });

        //单元号
        relativeLouceng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (louId == -1) {
                    Toast.makeText(HostmanRenActivity.this, "请先选择楼号", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, String> map = new HashMap<>();
                map.put("type", 3 + "");
                // TODO: 2019-10-11 louid提醒后台更新   现有id为0   值为空数组
                map.put("id", louId + "");  //错误   这里传入的是楼号的id
                //Log.i("www",extra.getId()+"");
                net(false, false).post(2, Api.GetHosing_URL, map);
            }
        });

        //门牌号
        relZhurenzhengDanyuanhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loucengId == -1) {
                    Toast.makeText(HostmanRenActivity.this, "请先选择楼层", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("type", 5 + "");
                map.put("id", loucengId + ""); //错误   这里传入的是楼层号的id
                net(false, false).post(3, Api.GetHosing_URL, map);
            }
        });

//        //楼号
//        textShenfen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (louId == -1) {
//                    Toast.makeText(HostmanRenActivity.this, "请先选择楼号", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                Map<String, String> map = new HashMap<>();
//                map.put("type", 5 + "");
//                map.put("id", danyuanId + ""); //错误   这里传入的是单元号的id
//                net(false, false).post(3, Api.GetHosing_URL, map);
//            }
//        });

        //楼层
        relZhurenzhengLouhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (danyuanId == -1) {
                    Toast.makeText(HostmanRenActivity.this, "请先选择单元", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("type", 4 + "");
                map.put("id", danyuanId + ""); //错误   这里传入的是单元号的id
                net(false, false).post(4, Api.GetHosing_URL, map);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menId == -1 || xiaoquId == -1 || louId == -1 || danyuanId == -1 || loucengId == -1 || shenfen == null || shenfen.isEmpty()) {
                    Toast.makeText(HostmanRenActivity.this, "请将资料填写完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (textMenpaihao.getText().equals("房主")) {
                    OwnerVerifyBean ownerVerifyBean = new OwnerVerifyBean();
                    ownerVerifyBean.setBuilding_id(louId);
                    ownerVerifyBean.setCommunity_id(xiaoquId);
                    ownerVerifyBean.setUnitdoor_id(danyuanId);
                    ownerVerifyBean.setFloors_id(loucengId);
                    ownerVerifyBean.setHouses_id(menId);

                    ownerVerifyBean.setToken(SharePreferenceHelper.getInstance(getApplicationContext()).getString("token", ""));
                    Intent intent = new Intent(HostmanRenActivity.this, FamilyIdentityActivity.class);
                    intent.putExtra("ownerVerifyBean", ownerVerifyBean);
                    intent.putExtra("relationship", shenfen);
                    startActivityForResult(intent,444);
                } else if (textMenpaihao.getText().equals("租客")) {
                    Intent intent = new Intent(HostmanRenActivity.this, TenementActivity.class);
                    intent.putExtra("community_id", xiaoquId);//小区id
                    intent.putExtra("building_id", louId);//楼号i
                    intent.putExtra("unitdoor_id", danyuanId);//单元id
                    intent.putExtra("floors_id", loucengId);//楼层id
                    intent.putExtra("houses_id", menId);//门牌号id
                    startActivity(intent);
                }

            }
        });

    }


    @OnClick({R.id.rel_xiaoqu, R.id.rel_zhurenzheng_shenfen, R.id.rel_zhurenzheng_louhao, R.id.rel_zhurenzheng_danyuanhao, R.id.rel_zhurenzheng_menpaihao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_xiaoqu:
                intent = new Intent(HostmanRenActivity.this, ChooseCommityActivity.class);
                startActivityForResult(intent, TO_CHOOSE_COMMITY_ACTIVITY_REQUEST_CODE);
                break;
            case R.id.rel_zhurenzheng_shenfen:
                break;
            case R.id.rel_zhurenzheng_louhao:
                break;
            case R.id.rel_zhurenzheng_danyuanhao:
                break;
            case R.id.rel_zhurenzheng_menpaihao:
                list = new ArrayList<>();
                list.add("房主");
                list.add("租客");
                picker = new SinglePicker<>(HostmanRenActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("身份选择");
                picker.setSelectedIndex(8);
                picker.setWheelModeEnable(false);
                //启用权重 setWeightWidth 才起作用
                picker.setWeightEnable(true);
                picker.setWeightWidth(1);
                picker.setSelectedTextColor(Color.BLUE);//前四位值是透明度
                picker.setUnSelectedTextColor(Color.GRAY);
                picker.setOnSingleWheelListener(new OnSingleWheelListener() {
                    @Override
                    public void onWheeled(int index, String item) {
                        textMenpaihao.setText(item);
                        shenfen = item.toString();


                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        textMenpaihao.setText(item);
                    }
                });
                picker.show();
                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            gson = new Gson();
            louHaoBean = gson.fromJson(data, LouHaoBean.class);
            louHaoBeanData = louHaoBean.getData();
            list = new ArrayList<>();
            list.clear();
            for (LouHaoBean.DataBean louHaoBeanDatum : louHaoBeanData) {
                list.add(louHaoBeanDatum.getName());
            }
            picker1 = new SinglePicker<>(HostmanRenActivity.this, list);
            picker1.setCanLoop(false);//不禁用循环
            picker1.setLineVisible(true);
            picker1.setTextSize(18);
            picker1.setTitleText("楼号选择");
            picker1.setSelectedIndex(8);
            picker1.setWheelModeEnable(false);
            //启用权重 setWeightWidth 才起作用
            picker1.setWeightEnable(true);
            picker1.setWeightWidth(1);
            picker1.setSelectedTextColor(Color.BLUE);//前四位值是透明度
            picker1.setUnSelectedTextColor(Color.GRAY);
            picker1.setOnSingleWheelListener(new OnSingleWheelListener() {
                @Override
                public void onWheeled(int index, String item) {
                    textShenfen.setText(item);
                }
            });
            picker1.setOnItemPickListener(new OnItemPickListener<String>() {
                @Override
                public void onItemPicked(int index, String item) {
                    textShenfen.setText(item);
                    //  设置成员变量  保存 楼号的louId    = index
                    louId = louHaoBeanData.get(index).getId();
                }
            });
            picker1.show();

        }

        if (type == 2) {

            gson = new Gson();
            unitNumber = gson.fromJson(data, UnitNumber.class);
            unitNumberData = unitNumber.getData();
            list = new ArrayList<>();
            list.clear();
            for (UnitNumber.DataBean unitNumberDatum : unitNumberData) {

                list.add(unitNumberDatum.getName());
            }
            picker2 = new SinglePicker<>(HostmanRenActivity.this, list);
            picker2.setCanLoop(false);//不禁用循环
            picker2.setLineVisible(true);
            picker2.setTextSize(18);
            picker2.setTitleText("单元号选择");
            picker2.setSelectedIndex(8);
            picker2.setWheelModeEnable(false);
            //启用权重 setWeightWidth 才起作用
            picker2.setWeightEnable(true);
            picker2.setWeightWidth(1);
            picker2.setSelectedTextColor(Color.BLUE);//前四位值是透明度
            picker2.setUnSelectedTextColor(Color.GRAY);
            picker2.setOnSingleWheelListener(new OnSingleWheelListener() {
                @Override
                public void onWheeled(int index, String item) {
                    textlouCengId.setText(item);
                }
            });
            picker2.setOnItemPickListener(new OnItemPickListener<String>() {
                @Override
                public void onItemPicked(int index, String item) {
                    textlouCengId.setText(item);
                    //  设置成员变量  保存 单元号的danyuanId    = index
                    danyuanId = unitNumberData.get(index).getId();
                }
            });
            picker2.show();

        }

        if (type == 3) {
            gson = new Gson();
            housenumberBean = gson.fromJson(data, HousenumberBean.class);
            housenumberBeanData = housenumberBean.getData();
            list = new ArrayList<>();
            list.clear();
            for (HousenumberBean.DataBean housenumberBeanDatum : housenumberBeanData) {

                list.add(housenumberBeanDatum.getName());
            }

            picker3 = new SinglePicker<>(HostmanRenActivity.this, list);
            picker3.setCanLoop(false);//不禁用循环
            picker3.setLineVisible(true);
            picker3.setTextSize(18);
            picker3.setTitleText("门牌号选择");
            picker3.setSelectedIndex(8);
            picker3.setWheelModeEnable(false);
            //启用权重 setWeightWidth 才起作用
            picker3.setWeightEnable(true);
            picker3.setWeightWidth(1);
            picker3.setSelectedTextColor(Color.BLUE);//前四位值是透明度
            picker3.setUnSelectedTextColor(Color.GRAY);
            picker3.setOnSingleWheelListener(new OnSingleWheelListener() {
                @Override
                public void onWheeled(int index, String item) {
                    textDanyuanhao.setText(item);
                }
            });
            picker3.setOnItemPickListener(new OnItemPickListener<String>() {
                @Override
                public void onItemPicked(int index, String item) {
                    textDanyuanhao.setText(item);
                    //  设置成员变量  保存 门牌号的menId    = index
                    menId = housenumberBeanData.get(index).getId();
                }
            });
            picker3.show();
        }

        if (type == 4) {
            gson = new Gson();
            LouCengBean louCengBean = gson.fromJson(data, LouCengBean.class);
            louCengBeanData = louCengBean.getData();
            list = new ArrayList<>();
            list.clear();
            for (LouCengBean.DataBean louCengBeanDatum : louCengBeanData) {
                list.add(louCengBeanDatum.getName());
            }
            picker4 = new SinglePicker<>(HostmanRenActivity.this, list);
            picker4.setCanLoop(false);//不禁用循环
            picker4.setLineVisible(true);
            picker4.setTextSize(18);
            picker4.setTitleText("楼层选择");
            picker4.setSelectedIndex(8);
            picker4.setWheelModeEnable(false);
            //启用权重 setWeightWidth 才起作用
            picker4.setWeightEnable(true);
            picker4.setWeightWidth(1);
            picker4.setSelectedTextColor(Color.BLUE);//前四位值是透明度
            picker4.setUnSelectedTextColor(Color.GRAY);
            picker4.setOnSingleWheelListener(new OnSingleWheelListener() {
                @Override
                public void onWheeled(int index, String item) {
                    textLouhao.setText(item);
                }
            });
            picker4.setOnItemPickListener(new OnItemPickListener<String>() {
                @Override
                public void onItemPicked(int index, String item) {
                    textLouhao.setText(item);
                    //  设置成员变量  保存 楼号的louId    = index
                    loucengId = louCengBeanData.get(index).getId();
                }
            });
            picker4.show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TO_CHOOSE_COMMITY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle b = data.getExtras();
            String string = b.getString("XIAO_QU_NAME");
            xiaoquId = b.getInt("XIAO_QU_ID");
            tvChooseXiaoqu.setText(string);
        }
        if(requestCode==444&&resultCode==444){
            finish();
        }
    }
}
