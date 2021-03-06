package com.sunshine.first.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
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

import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;
import com.sunshine.first.R;
import com.sunshine.first.bean.CheckBean;
import com.sunshine.first.bean.OwnerVerifyBean;
import com.sunshine.first.bean.ShowOwnerVerifyBean;
import com.sunshine.first.bean.UploadImgBean;
import com.sunshine.first.utils.SharePreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

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
 * 身份认证
 */
public class FamilyIdentityActivity extends BaseAppCompatActivity implements View.OnClickListener {


    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.rel_name)
    RelativeLayout relName;
    @BindView(R.id.view_my_fangkejilu)
    View viewMyFangkejilu;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rel_sex)
    RelativeLayout relSex;
    @BindView(R.id.view_zhurenzheng_two)
    View viewZhurenzhengTwo;
    @BindView(R.id.tv_phone_number)
    EditText tvPhoneNumber;
    @BindView(R.id.rel_phone)
    RelativeLayout relPhone;
    @BindView(R.id.view_zhurenzheng_three)
    View viewZhurenzhengThree;
    /*@BindView(R.id.tv_choose_relation)
    TextView tvChooseRelation;*/
   /* @BindView(R.id.rel_relationship)
    RelativeLayout relRelationship;*/
 /*   @BindView(R.id.view_zhurenzheng_four)
    View viewZhurenzhengFour;*/
    @BindView(R.id.tv_ID_number)
    EditText tvIDNumber;
    @BindView(R.id.rel_ID_number)
    RelativeLayout relIDNumber;
    @BindView(R.id.relative_id)
    LinearLayout relativeId;
    @BindView(R.id.tv_photos)
    TextView tvPhotos;
    @BindView(R.id.tv_grey)
    TextView tvGrey;
    @BindView(R.id.icon_head)
    ImageView iconHead;
    @BindView(R.id.rela_photo)
    RelativeLayout relaPhoto;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.linear_id)
    LinearLayout linearId;
    @BindView(R.id.icon_head_found)
    ImageView iconHeadFound;
    @BindView(R.id.rela_head_takephoto)
    RelativeLayout relaHeadTakephoto;
    @BindView(R.id.icon_china)
    ImageView iconChina;
    @BindView(R.id.rela_back_takephoto)
    RelativeLayout relaBackTakephoto;
    @BindView(R.id.relative_two)
    RelativeLayout relativeTwo;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private PopupWindow pop;
    private int maxSelectNum = 1;
    private String path;
    private String face;
    private int fz;
    private SinglePicker<String> picker;
    private ArrayList<String> list;

    private boolean isImgOne = false;
    private boolean isImgTwo = false;
    private boolean isImgThree = false;
    private String iconOne = "";
    private String iconTwo = "";
    private String iconThree = "";
    private int aa;
    private int cc = 1;
    private String relationship;

    @Override
    public int getLayoutId() {
        return R.layout.layout_family_identity;
    }

    @Override
    protected void initView() {
        setDefaultTitle("身份认证");

        relSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> list = new ArrayList<>();
                list.add("男");
                list.add("女");
                SinglePicker<String> picker = new SinglePicker<>(FamilyIdentityActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("性别");
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
                        tvSex.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvSex.setText(item);
                    }
                });
                picker.show();
            }
        });
        iconHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RxPermissions rxPermission = new RxPermissions(FamilyIdentityActivity.this);
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
                                    Toast.makeText(FamilyIdentityActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                isImgOne = true;
                isImgTwo = false;
                isImgThree = false;
            }
        });

        iconHeadFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RxPermissions rxPermission = new RxPermissions(FamilyIdentityActivity.this);
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
                                    Toast.makeText(FamilyIdentityActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                isImgOne = false;
                isImgTwo = true;
                isImgThree = false;
            }
        });


        iconChina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxPermissions rxPermission = new RxPermissions(FamilyIdentityActivity.this);
                rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {// 用户已经同意该权限
                                    //第一种方式，弹出选择和拍照的dialog
                                    popwindow(3);

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                                } else {
                                    Toast.makeText(FamilyIdentityActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                isImgOne = false;
                isImgTwo = false;
                isImgThree = true;
            }
        });


    }


    @Override
    protected void initData() {

        btnSubmit.setOnClickListener(this);
        relationship = getIntent().getStringExtra("relationship");
        //tvChooseRelation.setText(relationship);
        if ("房主".equals(relationship)) {

            cc = 1;

        } else if ("租客".equals(relationship)) {

            cc = 2;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                CheckBean checkBean = new CheckBean();
                if (cc == 1) {
                    OwnerVerifyBean ownerVerifyBean = (OwnerVerifyBean) getIntent().getSerializableExtra("ownerVerifyBean");
                    int building_id = ownerVerifyBean.getBuilding_id();
                    int community_id = ownerVerifyBean.getCommunity_id();
                    int floors_id = ownerVerifyBean.getFloors_id();
                    int houses_id = ownerVerifyBean.getHouses_id();
                    int unitdoor_id = ownerVerifyBean.getUnitdoor_id();


                    checkBean.setHouses_id(houses_id + "");
                    checkBean.setBuilding_id(building_id + "");
                    checkBean.setFloors_id(floors_id + "");
                    checkBean.setUnitdoor_id(unitdoor_id + "");
                    checkBean.setCommunity_id(community_id + "");
                }
                String token = SharePreferenceHelper.getInstance(this).getString("token", "");

                Log.i("tokens", token);

                String name = tvName.getText().toString();

                String sex = tvSex.getText().toString();

                if (sex.equals("男")) {
                    aa = 0;
                } else if (sex.equals("女")) {
                    aa = 1;
                }
                String phoneNumber = tvPhoneNumber.getText().toString();

                String IDNumber = tvIDNumber.getText().toString();

                checkBean.setCard_img_a(iconOne);
                checkBean.setCard_img_b(iconTwo);
                checkBean.setFace_recognition(iconThree);
                checkBean.setIdentity_card_number(IDNumber);
                checkBean.setResidents_mobile(phoneNumber);
                checkBean.setResidents_name(name);
                checkBean.setType(cc + "");
                // checkBean.setHouses_id(houses_id+"");
                checkBean.setToken(token);
                checkBean.setSex(aa + "");
                RequestBody body = (RequestBody) buildRequestBody(checkBean);
                if (cc == 2)
                    net(true, true).post(5, Api.Add_Residents, body);
                else
                    net(true, true).post(4, Api.OwnerVerify_URL, body);

                break;
        }
    }

    public static RequestBody buildRequestBody(Object object) {
        Gson gson = new Gson();
        String jsonData = gson.toJson(object);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonData);
        return body;
    }


    //弹出框的内容
    private void popwindow(final int pos) {
        View bottomView = View.inflate(FamilyIdentityActivity.this, R.layout.layout_bottom_dialog, null);
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
                        PictureSelector.create(FamilyIdentityActivity.this)
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
                        PictureSelector.create(FamilyIdentityActivity.this)
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
//                iconOne = path;
                upLoad(path, requestCode);

//                getUpdateImagePath(iconHead, requestCode);

            } else if (requestCode == 2) {

                iconHeadFound.setImageURI(uri);
//                iconTwo = path;
//                getUpdateImagePath(iconHeadFound, requestCode);
                upLoad(path, requestCode);
            } else if (requestCode == 3) {

                iconChina.setImageURI(uri);
//                iconThree = path;
//                getUpdateImagePath(iconChina, requestCode);
                upLoad(path, requestCode);
            }
        }
    }

    private void getUpdateImagePath(ImageView iconOne, int requestCode) {
        try {
            iconOne.setDrawingCacheEnabled(true);
            Bitmap bitmap = iconOne.getDrawingCache();
            String file = bitmapToBase64(bitmap);
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

    private void getImgPath() {
        File file = new File(path);
        Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        if (isImgOne == true) {
            iconHead.setBackground(Drawable.createFromPath(path));
            Map<String, String> map = new HashMap<>();
            map.put("isApp", "1");
            map.put("folder", "xier");  //错误   这里传入的是楼号的id
            map.put("disk", "xier");  //错误   这里传入的是楼号的id

            net(false, false).post(2, Api.UploadImg, map);
        }
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type == 1) {
            Gson gson = new Gson();
            UploadImgBean uploadImgBean = gson.fromJson(data, UploadImgBean.class);
            iconOne = uploadImgBean.getData().getImgUrl();
        }
        if (type == 2) {
            Gson gson = new Gson();
            UploadImgBean uploadImgBean = gson.fromJson(data, UploadImgBean.class);
            iconTwo = uploadImgBean.getData().getImgUrl();
        }
        if (type == 3) {
            Gson gson = new Gson();
            UploadImgBean uploadImgBean = gson.fromJson(data, UploadImgBean.class);
            iconThree = uploadImgBean.getData().getImgUrl();
        }
        if (type == 4 || type == 5) {
            Gson gson = new Gson();
            ShowOwnerVerifyBean showOwnerVerifyBean = gson.fromJson(data, ShowOwnerVerifyBean.class);
            Toast.makeText(FamilyIdentityActivity.this, showOwnerVerifyBean.getMessage().toString(), Toast.LENGTH_SHORT).show();
            setResult(444);
            finish();
        }
    }

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private void upLoad(String path, final int requestCode) {
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
                            final Request request = new Request.Builder()
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
                                            Toast.makeText(FamilyIdentityActivity.this, "失败", Toast.LENGTH_SHORT).show();
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
                                                if (requestCode == 1) {
                                                    iconOne = uploadImgBean.getData().getImgUrl();
                                                } else if (requestCode == 2) {
                                                    iconTwo = uploadImgBean.getData().getImgUrl();
                                                } else {
                                                    iconThree = uploadImgBean.getData().getImgUrl();
                                                }

                                                JSONObject jsonObject = new JSONObject(json);
                                                JSONObject jsonObject1 = jsonObject.optJSONObject("message");
                                                if (jsonObject1 != null) {
                                                    String message = jsonObject1.optString("message");
                                                    int code = jsonObject1.optInt("error_code");
                                                    if (0 == code) {

//                                                        finish();
                                                    }
                                                    Toast.makeText(FamilyIdentityActivity.this, message + "", Toast.LENGTH_SHORT).show();
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
