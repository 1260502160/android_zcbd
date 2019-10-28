package com.sunshine.first.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.AddCarBean;
import com.sunshine.first.bean.HouseListBean;
import com.sunshine.first.bean.UploadImgBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;
import io.reactivex.functions.Consumer;

/**
 * 添加车辆信息
 */

public class AddCarInfoActivity extends BaseAppCompatActivity {
    @BindView(R.id.relative_phone_number)
    RelativeLayout relativePhoneNumber;
    @BindView(R.id.tv_phone_number)
    EditText tvPhoneNumber;
    @BindView(R.id.relative_safe_password)
    RelativeLayout relativeSafePassword;
    @BindView(R.id.icon_add_one)
    ImageView iconAddOne;
    @BindView(R.id.icon_add_two)
    ImageView iconAddTwo;
    @BindView(R.id.btn_sure_add)
    Button btnSureAdd;
    @BindView(R.id.tv_commiunty_name)
    TextView tvCommiuntyName;
    private PopupWindow pop;
    private int maxSelectNum = 1;
    private String path;
    private String iconOne = "";
    private String iconTwo = "";
    private boolean isImgOne = false;
    private boolean isImgTwo = false;

    private List<HouseListBean.DataListBean> dataListBeans;
    private int community_id;
    private String token;

    @Override
    public int getLayoutId() {
        return R.layout.layout_car_certify;
    }

    @Override
    protected void initView() {

        setDefaultTitle("车辆信息");
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        btnSureAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                token = SharePreferenceHelper.getInstance(AddCarInfoActivity.this).getString("token", "");
                String cummiuntyname = tvCommiuntyName.getText().toString();
                String phonenumber = tvPhoneNumber.getText().toString();
                HashMap<String, String> map = new HashMap<>();
                map.put("token",token);
                map.put("comm_id",cummiuntyname);
                map.put("plate_num",phonenumber);
                map.put("license",iconOne);
                map.put("car_photo",iconTwo);
                net(false, false).post(4, Api.AddCar_URL, map);



            }
        });
        relativePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SharePreferenceHelper.getInstance(AddCarInfoActivity.this).getString("token", "");
                HashMap<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("type", "1");
                net(false, false).post(3, Api.HousesList_URL, map);

            }
        });

        iconAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxPermissions rxPermission = new RxPermissions(AddCarInfoActivity.this);
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
                                    Toast.makeText(AddCarInfoActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                isImgOne =true;
                isImgTwo = false;

            }
        });

        iconAddTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxPermissions rxPermission = new RxPermissions(AddCarInfoActivity.this);
                rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {// 用户已经同意该权限
                                    //第一种方式，弹出选择和拍照的dialog
                                    popwindow(2);

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                                } else {
                                    Toast.makeText(AddCarInfoActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                isImgOne =false;
                isImgTwo = true;

            }
        });

    }

    //弹出框的内容
    private void popwindow(final int pos) {
        View bottomView = View.inflate(AddCarInfoActivity.this, R.layout.layout_bottom_dialog, null);
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
                        PictureSelector.create(AddCarInfoActivity.this)
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
                        PictureSelector.create(AddCarInfoActivity.this)
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
                iconAddOne.setImageURI(uri);
//                iconOne = path;

                getUpdateImagePath(iconAddOne,requestCode);

            }else if (requestCode == 2){

                iconAddTwo.setImageURI(uri);
//                iconTwo = path;
                getUpdateImagePath(iconAddTwo,requestCode);
            }
        }
    }

    private void getUpdateImagePath(ImageView iconOne, int requestCode) {
        try {
            iconOne.setDrawingCacheEnabled(true);
            Bitmap bitmap=iconOne.getDrawingCache();
            String file = bitmapToBase64(bitmap);
            HashMap<String, String> map = new HashMap<>();
            map.put("image",file);
            map.put("folder","xier");
            map.put("disk","xier");
            map.put("isApp","1");
//                    ge(map);
            net(false, false).post(requestCode, Api.UploadImg, map);
//                    iconHead.setDrawingCacheEnabled(false);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    /**
     * bitmap转为base64
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
    private void getImgPath() {
        File file = new File(path);
        Toast.makeText(this,file.getAbsolutePath(),Toast.LENGTH_SHORT).show();
        if (isImgOne == true){
            iconAddOne.setBackground(Drawable.createFromPath(path));
            Map<String,String> map = new HashMap<>();
            map.put("isApp","1");
            map.put("folder","xier");  //错误   这里传入的是楼号的id
            map.put("disk","xier");  //错误   这里传入的是楼号的id
            net(false,false).post(2,Api.UploadImg,map);
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==3){

            Gson gson = new Gson();
            final HouseListBean houseListBean = gson.fromJson(data, HouseListBean.class);
            dataListBeans = houseListBean.getData().getList();
            ArrayList<String> list = new ArrayList<>();
            for (HouseListBean.DataListBean houseListBeanDatum : dataListBeans) {
                list.add(houseListBeanDatum.getCommunity_name()+houseListBeanDatum.getBuilding_name()+houseListBeanDatum.getUnitdoor_name()+houseListBeanDatum.getFloors_name()+houseListBeanDatum.getHouses_number_name());
            }

            SinglePicker<String> picker = new SinglePicker<>(AddCarInfoActivity.this, list);
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
        if (type==2){
            Gson gson = new Gson();
            UploadImgBean uploadImgBean = gson.fromJson(data, UploadImgBean.class);
            iconTwo = uploadImgBean.getData().getImgUrl();
        }
        if (type==1){
            Gson gson = new Gson();
            UploadImgBean uploadImgBean = gson.fromJson(data, UploadImgBean.class);
            iconOne = uploadImgBean.getData().getImgUrl();
        }
        if (type==4){
            Gson gson = new Gson();
            AddCarBean addCarBean = gson.fromJson(data, AddCarBean.class);
            Toast.makeText(AddCarInfoActivity.this,addCarBean.getMessage().toString(),Toast.LENGTH_SHORT).show();

        }

    }
}
