package com.sunshine.first.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;
import com.sunshine.first.R;
import com.sunshine.first.bean.AddAddressBean;
import com.sunshine.first.bean.UploadImgBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 租客身份认证
 */
public class TenementActivity extends BaseAppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_name_tenement)
    EditText et_name_tenement;//名字
    @BindView(R.id.tv_phone_number_tenement)
    EditText tv_phone_number_tenement;//手机号码
    @BindView(R.id.tv_ID_number_tenement)
    EditText tv_ID_number_tenement;//身份证号码
    @BindView(R.id.tv_sex_tenement)
    TextView tv_sex_tenement;//
    @BindView(R.id.tv_choose_relation)
    TextView tv_choose_relation;//

    @BindView(R.id.icon_head_one_tenement)
    ImageView icon_head_one_tenement;//头像页面
    @BindView(R.id.icon_china_tenement)
    ImageView icon_china_tenement;//国徽页面

    @BindView(R.id.icon_tenement_tenement)
    ImageView icon_tenement_tenement;//房本1
    @BindView(R.id.icon_house_prove_tenement)
    ImageView icon_house_prove_tenement;//房本2


    @BindView(R.id.icon_face_tenement)
    ImageView icon_face_tenement;//人脸


    private PopupWindow pop;
    private int maxSelectNum = 1;
    private String path;


    private int sex = 0;//性别0男1女
    private int tenementType = 1;//认证类型1房主2租客
    private RxPermissions rxPermissions;
    private String head_one, head_two;
    private String house_one, house_two;
    private String faceImage;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setDefaultTitle("身份认证");
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_tenement;
    }

    private SinglePicker<String> sexPicker;//选择性别
    private SinglePicker<String> hostPicker;//选择租客或者房主

    @OnClick({R.id.rel_sex_tenement, R.id.rel_relationship_tenement, R.id.rl_head_one_tenement, R.id.rl_head_two_tenement,
            R.id.rl_houses_photo_tenement, R.id.rl_houses_photo_two_tenement, R.id.btn_submit_tenement, R.id.rl_face_photo_tenement})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_face_photo_tenement://人脸识别
                rxPermissions = new RxPermissions(TenementActivity.this);
                rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {// 用户已经同意该权限
                                    //第一种方式，弹出选择和拍照的dialog
                                    popwindow(10);

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                                } else {
                                    Toast.makeText(TenementActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.btn_submit_tenement://提交
                String phoneNumber = tv_phone_number_tenement.getText().toString().trim();
                String name = et_name_tenement.getText().toString().trim();
                String idNumber = tv_ID_number_tenement.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastManage.s(this, "请输入姓名！");
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    ToastManage.s(this, "请输入手机号码！");
                    return;
                }
                if (TextUtils.isEmpty(idNumber)) {
                    ToastManage.s(this, "请输入身份证号码！");
                    return;
                }
                if (TextUtils.isEmpty(faceImage)) {
                    ToastManage.s(this, "人脸识别照片必须上传！");
                    return;
                }

                hashMap.clear();
                hashMap.put("token", getToken());
                hashMap.put("community_id", getIntent().getIntExtra("community_id", 0) + "");//小区id

                hashMap.put("building_id", getIntent().getIntExtra("building_id", 0) + "");//楼号id

                hashMap.put("unitdoor_id", getIntent().getIntExtra("unitdoor_id", 0) + "");//单元id

                hashMap.put("floors_id", getIntent().getIntExtra("floors_id", 0) + "");//楼层id

                hashMap.put("houses_id", getIntent().getIntExtra("houses_id", 0) + "");//门牌号id

                hashMap.put("residents_name", name);//姓名

                hashMap.put("residents_mobile", phoneNumber);//手机号

                hashMap.put("type", "2");//认证类型1房主2租客

                hashMap.put("identity_card_number", idNumber);//身份证号码

                hashMap.put("sex", sex + "");//性别0男1女

                hashMap.put("face_recognition", faceImage);//人脸识别图片
                if (!TextUtils.isEmpty(head_one))
                    hashMap.put("card_img_a", head_one);//身份证正面
                if (!TextUtils.isEmpty(head_two))
                    hashMap.put("card_img_b", head_two);//身份证反面
                String photoUrl = "";
                if (!TextUtils.isEmpty(house_one)) {
                    photoUrl = house_one;
                }
                if (!TextUtils.isEmpty(house_two)) {
                    if (!TextUtils.isEmpty(photoUrl)) {
                        photoUrl = photoUrl + ",";
                    }
                    photoUrl += house_two;
                }
                if (!TextUtils.isEmpty(photoUrl))
                    hashMap.put("contract", photoUrl);//租房合同图片多个以逗号隔开

                net(true, false).post(11, Api.OwnerVerify_URL, hashMap);
                break;
            case R.id.rl_houses_photo_two_tenement://房屋证明第二个照片
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
            case R.id.rl_houses_photo_tenement://房屋证明第一个照片
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
            case R.id.rl_head_two_tenement://身份证照片第二个
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
            case R.id.rl_head_one_tenement://身份证照片第一个
                rxPermissions = new RxPermissions(TenementActivity.this);
                rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
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
            case R.id.rel_sex_tenement://性别
                if (sexPicker == null) {
                    ArrayList sexList = new ArrayList<>();
                    sexList.clear();
                    sexList.add("男");
                    sexList.add("女");
                    sexPicker = new SinglePicker<>(this, sexList);
                    sexPicker.setCanLoop(false);//不禁用循环
                    sexPicker.setLineVisible(true);
                    sexPicker.setTextSize(18);
                    sexPicker.setTitleText("性别");
                    sexPicker.setSelectedIndex(8);
                    sexPicker.setWheelModeEnable(false);
                    //启用权重 setWeightWidth 才起作用
                    sexPicker.setWeightEnable(true);
                    sexPicker.setWeightWidth(1);
                    sexPicker.setSelectedTextColor(Color.BLACK);//前四位值是透明度
                    sexPicker.setUnSelectedTextColor(Color.GRAY);
                    sexPicker.setOnSingleWheelListener(new OnSingleWheelListener() {
                        @Override
                        public void onWheeled(int index, String item) {
//                        tvSex.setText(item);
                        }
                    });
                    sexPicker.setOnItemPickListener(new OnItemPickListener<String>() {
                        @Override
                        public void onItemPicked(int index, String item) {
                            tv_sex_tenement.setText(item);
                            sex = index;
                        }
                    });
                }
                sexPicker.show();

                break;
            case R.id.rel_relationship_tenement://与房主关系
                if (hostPicker == null) {
                    ArrayList list = new ArrayList<>();
                    list.add("房主");
                    list.add("租客");
                    hostPicker = new SinglePicker<>(this, list);
                    hostPicker.setCanLoop(false);//不禁用循环
                    hostPicker.setLineVisible(true);
                    hostPicker.setTextSize(18);
                    hostPicker.setTitleText("身份选择");
                    hostPicker.setSelectedIndex(8);
                    hostPicker.setWheelModeEnable(false);
                    //启用权重 setWeightWidth 才起作用
                    hostPicker.setWeightEnable(true);
                    hostPicker.setWeightWidth(1);
                    hostPicker.setSelectedTextColor(Color.BLUE);//前四位值是透明度
                    hostPicker.setUnSelectedTextColor(Color.GRAY);
                    hostPicker.setOnSingleWheelListener(new OnSingleWheelListener() {
                        @Override
                        public void onWheeled(int index, String item) {


                        }
                    });
                    hostPicker.setOnItemPickListener(new OnItemPickListener<String>() {
                        @Override
                        public void onItemPicked(int index, String item) {
                            tv_choose_relation.setText(item);
                            tenementType = (index + 1);
                        }
                    });
                }

                hostPicker.show();

                break;
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (11 == type) {//提交
            AddAddressBean addAddressBean = gson.fromJson(data, AddAddressBean.class);
            if (addAddressBean != null) {
                ToastManage.s(this, addAddressBean.message + "");
                if ("200".equals(addAddressBean.error_code)) {
                    finish();
                }
            }
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

            Uri uri = Uri.parse(path);
            if (requestCode == 1) {

                icon_head_one_tenement.setImageURI(uri);

                upLoad(path, 1);


            } else if (requestCode == 2) {

                icon_china_tenement.setImageURI(uri);

                upLoad(path, 2);

            } else if (requestCode == 3) {

                icon_tenement_tenement.setImageURI(uri);

                upLoad(path, 3);

            } else if (requestCode == 4) {

                icon_house_prove_tenement.setImageURI(uri);

                upLoad(path, 4);

            } else if (requestCode == 10) {//人脸的
                icon_face_tenement.setImageURI(uri);

                upLoad(path, 10);

            }
        }
    }


    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private void upLoad(String path, final int type) {
        if (!TextUtils.isEmpty(path)) {
            Luban.with(this)
                    .load(path)
                    .ignoreBy(100)
                    .setTargetDir(getPath())
                    .filter(new CompressionPredicate() {
                        @Override
                        public boolean apply(String path) {
                            return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                        }
                    })
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
                            // TODO 压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            // TODO 压缩成功后调用，返回压缩后的图片文件
                            OkHttpClient okHttpClient = new OkHttpClient();
                            MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                            mbody.addFormDataPart("image", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
//            mbody.addFormDataPart("isApp", "1");
                            mbody.addFormDataPart("folder", "xier");
                            mbody.addFormDataPart("disk", "xier");

                            RequestBody requestBody = mbody.build();
                            Request request = new Request.Builder()
                                    .url(Api.UploadImg)
                                    .post(requestBody)
                                    .build();
                            Call call = okHttpClient.newCall(request);
                            call.enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    Log.e("TAG", "onFailure: " + e);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(TenementActivity.this, "失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    final String json = response.body().string();
                                    Log.e("TAG", "成功" + json);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                UploadImgBean uploadImgBean = gson.fromJson(json, UploadImgBean.class);
                                                if (type == 1) {
                                                    head_one = uploadImgBean.getData().getImgUrl();
                                                    head_one = uploadImgBean.getData().getImgUrl();
                                                } else if (type == 2) {
                                                    head_two = uploadImgBean.getData().getImgUrl();
                                                } else if (type == 3) {
                                                    house_one = uploadImgBean.getData().getImgUrl();
                                                } else if (type == 10) {
                                                    faceImage = uploadImgBean.getData().getImgUrl();
                                                } else {
                                                    house_two = uploadImgBean.getData().getImgUrl();
                                                }


                                                JSONObject jsonObject = new JSONObject(json);
                                                JSONObject jsonObject1 = jsonObject.optJSONObject("message");
                                                if (jsonObject1 != null) {
                                                    String message = jsonObject1.optString("message");
                                                    int code = jsonObject1.optInt("error_code");
                                                    if (0 == code) {

                                                        finish();
                                                    }
                                                    Toast.makeText(TenementActivity.this, message + "", Toast.LENGTH_SHORT).show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            });
                        }

                        @Override
                        public void onError(Throwable e) {
                            // TODO 当压缩过程出现问题时调用
                        }
                    }).launch();

        } else {
            ToastManage.s(this, "请选择文件或者输入内容！");
        }

    }
}
