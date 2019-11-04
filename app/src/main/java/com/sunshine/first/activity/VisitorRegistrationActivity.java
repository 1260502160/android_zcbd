package com.sunshine.first.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.base.model.Api;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.HouseListBean;
import com.sunshine.first.bean.UploadImgBean;
import com.sunshine.first.bean.VisitorAddBean;
import com.sunshine.first.bean.VisitorBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 访客登记
 */
public class VisitorRegistrationActivity extends BaseAppCompatActivity {

    @BindView(R.id.icon_next)
    ImageView iconNext;
    @BindView(R.id.icon_head)
    ImageView iconHead;
    @BindView(R.id.tv_commiunty_name)
    TextView tvCommiuntyName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.edit_ivationame)
    EditText editIvationame;
    @BindView(R.id.edit_ivphone)
    EditText editIvphone;
    @BindView(R.id.edit_car_one)
    EditText editCarOne;
    @BindView(R.id.edit_car_two)
    EditText editCarTwo;
    @BindView(R.id.edit_car_three)
    EditText editCarThree;
    @BindView(R.id.edit_car_four)
    EditText editCarFour;
    @BindView(R.id.edit_car_five)
    EditText editCarFive;
    @BindView(R.id.edit_car_six)
    EditText editCarSix;
    @BindView(R.id.edit_car_seven)
    EditText editCarSeven;
    @BindView(R.id.edit_car_eight)
    EditText editCarEight;
    @BindView(R.id.edit_car_nine)
    EditText editCarNine;
    @BindView(R.id.relative_visitor_time)
    RelativeLayout relativeVisitorTime;
    @BindView(R.id.relative_home_ren)
    RelativeLayout relativeHomeRen;
    @BindView(R.id.relative_visitor_name)
    RelativeLayout relativeVisitorName;
    @BindView(R.id.relative_visitor_phonenumber)
    RelativeLayout relativeVisitorPhonenumber;
    @BindView(R.id.relative_take_photo)
    RelativeLayout relativeTakePhoto;
    @BindView(R.id.btn_submit_visitor)
    Button btnSubmitVisitor;
    @BindView(R.id.linear_home_ren)
    LinearLayout linearHomeRen;
    private View iconback;
    private int maxSelectNum = 1;
    private String path;
    private PopupWindow pop;
    private boolean isImgOne = false;
    private String iconTwo = "";
    private String file;
    private Gson gson;
    private int community_id, building_id, unitdoor_id, floors_id, houses_id;
    private List<HouseListBean.DataListBean> houseListBeanData;


    @Override
    public int getLayoutId() {
        return R.layout.layout_visitor_registration;
    }

    @Override
    protected void initView() {

        setDefaultTitle("访客登记");
        setRightTitle("访客记录", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VisitorRegistrationActivity.this, VisitorRecordActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        final int verify = SharePreferenceHelper.getInstance(VisitorRegistrationActivity.this).getInt("is_verify", -1);
        Log.d("verify", "onViewClicked: " + verify);
        if (verify == 1) {

            linearHomeRen.setVisibility(View.GONE);
            relativeHomeRen.setVisibility(View.VISIBLE);
        } else {
            linearHomeRen.setVisibility(View.VISIBLE);
            relativeHomeRen.setVisibility(View.GONE);
            linearHomeRen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(VisitorRegistrationActivity.this, YeZhuRenZhengActivity.class);
                    startActivity(intent);
                }
            });
        }
        iconNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SharePreferenceHelper.getInstance(VisitorRegistrationActivity.this).getString("token", "");
                HashMap<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("type", "1");
                net(false, false).post(1, Api.HousesList_URL, map);
            }
        });

        btnSubmitVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VisitorBean visitorBean = new VisitorBean();
                String token = SharePreferenceHelper.getInstance(VisitorRegistrationActivity.this).getString("token", "");
                String time = tvTime.getText().toString();
                String name = editIvationame.getText().toString();
                String phone = editIvphone.getText().toString();
                String carone = editCarOne.getText().toString();
                String cartwo = editCarTwo.getText().toString();
                String carthree = editCarThree.getText().toString();
                String carfour = editCarFour.getText().toString();
                String carfive = editCarFive.getText().toString();
                String carsix = editCarSix.getText().toString();
                String carseven = editCarSeven.getText().toString();
                String careight = editCarEight.getText().toString();
                String carnine = editCarNine.getText().toString();

                /*  map.put("community_id",1+"");*/
                //map.put("building_id",1+"");
               /* map.put("unitdoor_id",10+"");
                map.put("floors_id",1+"");
                map.put("houses_id",27+"");*/

                visitorBean.setToken(token);
                visitorBean.setHouses_id(houses_id + "");
                visitorBean.setFloors_id(floors_id + "");
                visitorBean.setBuilding_id(building_id + "");
                visitorBean.setUnitdoor_id(unitdoor_id + "");
                visitorBean.setCommunity_id(community_id + "");
                visitorBean.setVisi_imgs(iconTwo);
                visitorBean.setCar_num(carone + cartwo + carthree + carfour + carfive + carsix + carseven + careight + carnine);
                visitorBean.setTime(time);
                visitorBean.setVisi_name(name);
                visitorBean.setVisi_mobile(phone);

                RequestBody body = (RequestBody) buildRequestBody(visitorBean);
                net(false, false).post(3, Api.VisitorAdd_URL, body);
                //net(false, false).post(3, Api.VisitorAdd_URL,map);

            }
        });

        iconHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxPermissions rxPermission = new RxPermissions(VisitorRegistrationActivity.this);
                rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {// 用户已经同意该权限
                                    //第一种方式，弹出选择和拍照的dialog
                                    popwindow(1);

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                                } else {
                                    Toast.makeText(VisitorRegistrationActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    //弹出框的内容
    private void popwindow(final int pos) {
        View bottomView = View.inflate(VisitorRegistrationActivity.this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = bottomView.findViewById(R.id.tv_album);
        TextView mCamera = bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(VisitorRegistrationActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(pos);
                        // .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(VisitorRegistrationActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(pos);
                        // .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_cancel:
                        //取消
                        //closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }

    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }

    public static RequestBody buildRequestBody(Object object) {
        Gson gson = new Gson();
        String jsonData = gson.toJson(object);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData);
        return body;
    }

    private void getUpdateImagePath(ImageView iconOne, int requestCode) {
        try {
            iconOne.setDrawingCacheEnabled(true);
            Bitmap bitmap = iconOne.getDrawingCache();
            file = bitmapToBase64(bitmap);
            HashMap<String, String> map = new HashMap<>();
            map.put("image", file);
            map.put("folder", "xier");
            map.put("disk", "xier");
            map.put("isApp", "1");
//                    ge(map);
            net(false, false).post(requestCode, Api.UploadImg, map);
//                    iconHead.setDrawingCacheEnabled(false);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {


            images = PictureSelector.obtainMultipleResult(data);

            LocalMedia media = images.get(0);
            int mimeType = media.getMimeType();

            if (media.isCut() && !media.isCompressed()) {
                // 裁剪过
                path = media.getCutPath();
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getCompressPath();
            } else {
                // 原图
                path = media.getPath();
            }
            // 图片
            if (media.isCompressed()) {
                Log.i("compress image result:", new File(media.getCompressPath()).length() / 1024 + "k");
                Log.i("压缩地址::", media.getCompressPath());
            }

            Log.i("原图地址::", media.getPath());
            int pictureType = PictureMimeType.isPictureType(media.getPictureType());
            if (media.isCut()) {
                Log.i("裁剪地址::", media.getCutPath());
            }
            long duration = media.getDuration();
//                getImgPath();
//                okHttpUpload("file", path, new UploadCallback() {
//                    @Override
//                    public void onResponse(Call call, Response response) {
//                        String str = response.body().toString();
//                        Log.i("lfq", response.message() + " , body " + str);
//                    }
//
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.i("IOException",e.getMessage());
//                    }
//                });
            Uri uri = Uri.parse(path);
            if (requestCode == 1) {
                // 图片选择结果回调
//                GlideUtils.loadRoundImg(FamilyIdentityActivity.this,path,iconHead);
                iconHead.setImageURI(uri);
//                iconOne = path;
                getUpdateImagePath(iconHead, 2);

            }
        }
    }


    @OnClick({R.id.relative_visitor_time, R.id.relative_visitor_name, R.id.relative_visitor_phonenumber, R.id.relative_take_photo, R.id.btn_submit_visitor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relative_visitor_time:
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tvTime.setText((getTime(date)));

                    }
                }).setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                //.setTitleText("Title")//标题文字
//                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(true)//是否循环滚动
//                //.setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                //.setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
////                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
////                .setRangDate(startDate,endDate)//起始终止年月日设定
                        .setLabel("年", "月", "日", "时", "分", "秒")
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        //.isDialog(true)//是否显示为对话框样式
                        .build();

                pvTime.show();
                break;
            case R.id.relative_visitor_name:
                break;
            case R.id.relative_visitor_phonenumber:
                break;
            case R.id.relative_take_photo:
                break;
            case R.id.icon_next:
                break;

            case R.id.btn_submit_visitor:
                break;
        }


    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return format.format(date);
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            Gson gson = new Gson();
            final HouseListBean houseListBean = gson.fromJson(data, HouseListBean.class);
            houseListBeanData = houseListBean.getData().getList();
            ArrayList<String> list = new ArrayList<>();
            for (HouseListBean.DataListBean houseListBeanDatum : houseListBeanData) {
                list.add(houseListBeanDatum.getCommunity_name() + houseListBeanDatum.getBuilding_name() + houseListBeanDatum.getUnitdoor_name() + houseListBeanDatum.getFloors_name() + houseListBeanDatum.getHouses_number_name());
            }

            SinglePicker<String> picker = new SinglePicker<>(VisitorRegistrationActivity.this, list);
            picker.setCanLoop(false);//不禁用循环
            picker.setLineVisible(true);
            picker.setTextSize(18);
            picker.setTitleText("房屋选择");
            picker.setSelectedIndex(8);
            picker.setWheelModeEnable(false);
            //启用权重 setWeightWidth 才起作用
            picker.setWeightEnable(true);
            picker.setWeightWidth(1);
            picker.setSelectedTextColor(Color.BLACK);//前四位值是透明度
            picker.setUnSelectedTextColor(Color.GRAY);
            picker.setOnSingleWheelListener(new OnSingleWheelListener() {
                @Override
                public void onWheeled(int index, String item) {
                    tvCommiuntyName.setText(item);
                }
            });
            picker.setOnItemPickListener(new OnItemPickListener<String>() {
                @Override
                public void onItemPicked(int index, String item) {
                    tvCommiuntyName.setText(item);
                    //楼号选择的忘了？
                    community_id = houseListBean.getData().getList().get(index).getCommunity_id();
                    //houseListBean.getData().get(index)

                }
            });
            picker.show();
        }
        if (type == 2) {
            Gson gson = new Gson();
            UploadImgBean uploadImgBean = gson.fromJson(data, UploadImgBean.class);
            iconTwo = uploadImgBean.getData().getImgUrl();
        }
        if (type == 3) {
            Log.i("VISITORREGISTRATION", data.toString());
            Gson gson = new Gson();
            VisitorAddBean visitorAddBean = gson.fromJson(data, VisitorAddBean.class);
            Log.i("visitorAddBean", visitorAddBean.toString());
            Toast.makeText(VisitorRegistrationActivity.this, visitorAddBean.getMessage().toString(), Toast.LENGTH_SHORT).show();

        }
    }


}
