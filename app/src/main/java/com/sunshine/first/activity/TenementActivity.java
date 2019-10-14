package com.sunshine.first.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.abner.ming.base.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;
import com.sunshine.first.R;

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

public class TenementActivity extends BaseAppCompatActivity implements View.OnClickListener{

    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.text_forget)
    TextView textForget;
    @BindView(R.id.view_fangzhurenzheng_one)
    View viewFangzhurenzhengOne;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.view_my_fangkejilu)
    View viewMyFangkejilu;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.view_zhurenzheng_two)
    View viewZhurenzhengTwo;
    @BindView(R.id.tv_phone_number)
    EditText tvPhoneNumber;
    @BindView(R.id.view_zhurenzheng_three)
    View viewZhurenzhengThree;
    @BindView(R.id.tv_choose_relation)
    TextView tvChooseRelation;
    @BindView(R.id.view_zhurenzheng_four)
    View viewZhurenzhengFour;
    @BindView(R.id.tv_ID_number)
    EditText tvIDNumber;
    @BindView(R.id.view_family_two)
    View viewFamilyTwo;
    @BindView(R.id.icon_head)
    ImageView iconHead;
    @BindView(R.id.icon_china)
    ImageView iconChina;
    @BindView(R.id.icon_tenement)
    ImageView iconTenement;
    @BindView(R.id.icon_house_prove)
    ImageView iconHouseProve;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private PopupWindow pop;
    private int maxSelectNum = 1;
    private String path;
    private  int bb;
    private boolean isImgOne = false;
    private boolean isImgTwo = false;
    private boolean isImgThree = false;
    private String iconOne = "";
    private String iconTwo = "";
    private String iconThree = "";
    private String iconFour = "";
    private RxPermissions rxPermissions;
    private String relationship;
    private int dd;

    @Override
    protected void initData() {

        iconBack.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvSex.setOnClickListener(this);
        tvPhoneNumber.setOnClickListener(this);
        tvChooseRelation.setOnClickListener(this);
        tvIDNumber.setOnClickListener(this);
        iconHead.setOnClickListener(this);
        iconChina.setOnClickListener(this);
        iconTenement.setOnClickListener(this);
        iconHouseProve.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        relationship = getIntent().getStringExtra("relationship");
        tvChooseRelation.setText(relationship);
        if (tvChooseRelation.equals("房主")){
            dd=1;

        }else if (tvChooseRelation.equals("租客")){
            dd=2;
        }


    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_tenement;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_name:

                break;
            case R.id.tv_sex:
                break;
            case R.id.tv_phone_number:
                break;
            case R.id.tv_choose_relation:

                break;
            case R.id.tv_ID_number:
                break;
            case R.id.icon_head:
                RxPermissions rxPermission = new RxPermissions(TenementActivity.this);
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
                                    Toast.makeText(TenementActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.icon_china:
                rxPermissions = new RxPermissions(TenementActivity.this);
                rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {// 用户已经同意该权限
                                    //第一种方式，弹出选择和拍照的dialog
                                    popwindow(2);

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                                } else {
                                    Toast.makeText(TenementActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.icon_tenement:
                rxPermissions = new RxPermissions(TenementActivity.this);
                rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {// 用户已经同意该权限
                                    //第一种方式，弹出选择和拍照的dialog
                                    popwindow(3);

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                                } else {
                                    Toast.makeText(TenementActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.icon_house_prove:
                rxPermissions = new RxPermissions(TenementActivity.this);
                rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {// 用户已经同意该权限
                                    //第一种方式，弹出选择和拍照的dialog
                                    popwindow(4);

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                                } else {
                                    Toast.makeText(TenementActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.btn_submit:

                String name = tvName.getText().toString();
                String sex =tvSex.getText().toString();

                if (sex.equals("男")){
                    bb=0;
                }else if(sex.equals("女")){
                    bb=1;
                }
                String phonenumber = tvPhoneNumber.getText().toString();
                String relation = tvChooseRelation.getText().toString();
                String idnumber = tvIDNumber.getText().toString();
                Map<String,String> map = new HashMap<>();
                map.put("residents_name",name);
                map.put("sex",sex);
                map.put("residents_mobile",phonenumber);
                map.put("type",1+"");
                map.put("identity_card_number",idnumber);
                break;

        }
    }

    //弹出框的内容
    private void popwindow(final int pos) {
        View bottomView = View.inflate(TenementActivity.this, R.layout.layout_bottom_dialog, null);
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
                        PictureSelector.create(TenementActivity.this)
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
                        PictureSelector.create(TenementActivity.this)
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
                iconHead.setImageURI(uri);
                iconOne = path;


                try {
                    iconHead.setDrawingCacheEnabled(true);
                    Log.e("bitmap","bitmap 1");
                    Bitmap bitmap=iconHead.getDrawingCache();
                    Log.e("bitmap","bitmap 2"+bitmap);


//                    Bitmap bitmap = getBitmapFormUri(this,uri);
//                    GlideUtils.loadRoundImg(FamilyIdentityActivity.this,path,iconHead);
//                    iconHead.setImageBitmap(bitmap);
                    String file = bitmapToBase64(bitmap);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("image",file);
                    map.put("folder","xier");
                    map.put("disk","xier");
                    map.put("isApp","1");
//                    ge(map);
                    net(false, false).post(2, Api.UploadImg, map);
//                    iconHead.setDrawingCacheEnabled(false);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }else if (requestCode == 2){

                iconChina.setImageURI(uri);
                iconTwo = path;

            }else if (requestCode == 3){

                iconTenement.setImageURI(uri);
                iconThree = path;

            }else if (requestCode == 4){

                iconHouseProve.setImageURI(uri);
                iconFour = path;

            }
        }
    }

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
}
