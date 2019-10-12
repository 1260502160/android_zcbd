package com.sunshine.first.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.abner.ming.base.BaseAppCompatActivity;
import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;
import com.qiniu.android.http.ResponseInfo;
import com.sunshine.first.R;
import com.sunshine.first.bean.UploadImgBean;
import com.sunshine.first.utils.GlideUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
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

public class FamilyIdentityActivity extends BaseAppCompatActivity {


    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.text_forget)
    TextView textForget;
    @BindView(R.id.relative_change)
    RelativeLayout relativeChange;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.rel_name)
    RelativeLayout relName;
    @BindView(R.id.view_my_fangkejilu)
    View viewMyFangkejilu;
    @BindView(R.id.tv_sex)
    EditText tvSex;
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
    @BindView(R.id.tv_choose_relation)
    EditText tvChooseRelation;
    @BindView(R.id.rel_relationship)
    RelativeLayout relRelationship;
    @BindView(R.id.view_zhurenzheng_four)
    View viewZhurenzhengFour;
    @BindView(R.id.tv_ID_number)
    EditText tvIDNumber;
    @BindView(R.id.rel_ID_number)
    RelativeLayout relIDNumber;
    @BindView(R.id.relative_id)
    RelativeLayout relativeId;
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
    private SinglePicker<String> picker;
    private ArrayList<String> list;

    private boolean isImgOne = false;
    private boolean isImgTwo = false;
    private boolean isImgThree = false;

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
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
                                    popwindow();

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                                } else {
                                    Toast.makeText(FamilyIdentityActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                isImgOne =true;
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
                                    popwindow();

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                                } else {
                                    Toast.makeText(FamilyIdentityActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                isImgOne =false;
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
                                    popwindow();

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                                } else {
                                    Toast.makeText(FamilyIdentityActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                isImgOne =false;
                isImgTwo = false;
                isImgThree = true;
            }
        });

        relRelationship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = new ArrayList<>();
                list.add("业主");
                list.add("亲友");
                list.add("家属");
                list.add("其他");
                picker = new SinglePicker<>(FamilyIdentityActivity.this, list);
                picker.setCanLoop(false);//不禁用循环
                picker.setLineVisible(true);
                picker.setTextSize(18);
                picker.setTitleText("关系选择");
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
                        tvChooseRelation.setText(item);
                    }
                });
                picker.setOnItemPickListener(new OnItemPickListener<String>() {
                    @Override
                    public void onItemPicked(int index, String item) {
                        tvChooseRelation.setText(item);
                    }
                });
                picker.show();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_family_identity;
    }

    //弹出框的内容
    private void popwindow() {
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
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(FamilyIdentityActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
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

            if (requestCode == PictureConfig.CHOOSE_REQUEST) {// 图片选择结果回调

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
                okHttpUpload("file", path, new UploadCallback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        String str = response.body().toString();
                        Log.i("lfq", response.message() + " , body " + str);
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("IOException",e.getMessage());
                    }
                });
                //GlideUtils.loadRoundImg(FamilyIdentityActivity.this,path,ivTouXiang);
            }
        }
    }

    private void getImgPath() {
        File file = new File(path);
        Toast.makeText(this,file.getAbsolutePath(),Toast.LENGTH_SHORT).show();
        if (isImgOne == true){
            iconHead.setBackground(Drawable.createFromPath(path));
            Map<String,String> map = new HashMap<>();

            map.put("folder","xier");  //错误   这里传入的是楼号的id
            map.put("disk","xier");  //错误   这里传入的是楼号的id

            net(false,false).post(2,Api.UploadImg,map);
        }
    }

    public static void okHttpUpload(String partName, String path, final UploadCallback callback){
        File file = new File(path);             // 需要上传的文件
        RequestBody requestFile =               // 根据文件格式封装文件
                RequestBody.create(MediaType.parse("image/*"), file);

        // 初始化请求体对象，设置Content-Type以及文件数据流
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)            // multipart/form-data
                .addFormDataPart(partName, file.getName(), requestFile)
                .build();

        // 封装OkHttp请求对象，初始化请求参数
        Request request = new Request.Builder()
                .url(Api.UploadImg)                // 上传url地址
                .post(requestBody)              // post请求体
                .build();

        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient  = httpBuilder
                .connectTimeout(100, TimeUnit.SECONDS)          // 设置请求超时时间
                .writeTimeout(150, TimeUnit.SECONDS)
                .build();
        // 发起异步网络请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (callback != null){
                    callback.onResponse(call, response);
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null){
                    callback.onFailure(call, e);
                }
            }
        });
    }

    public  File uriToFile(Uri uri, Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA }, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = { MediaStore.Images.Media.DATA };
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {
            //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }

    @Override
    public void success(int type, String data) {
        super.success(type, data);
        if (type==2){
            Gson gson = new Gson();
            UploadImgBean uploadImgBean = gson.fromJson(data, UploadImgBean.class);
            String imgUrl = uploadImgBean.getData().getImgUrl();
        }
    }

    private  interface UploadCallback {
        void onResponse(Call call, Response response);

        void onFailure(Call call, IOException e);
    }
}
