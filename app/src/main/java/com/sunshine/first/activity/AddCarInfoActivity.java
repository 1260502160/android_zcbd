package com.sunshine.first.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
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

import com.abner.ming.base.model.Api;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.ToastManage;
import com.sunshine.first.BaseAppCompatActivity;
import com.sunshine.first.R;
import com.sunshine.first.bean.AddCarBean;
import com.sunshine.first.bean.HouseListBean;
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
    @BindView(R.id.ll_add_one)
    LinearLayout ll_add_one;
    @BindView(R.id.ll_add_two)
    LinearLayout ll_add_two;

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
    private int communityId;


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

                String cummiuntyname = tvCommiuntyName.getText().toString();

                if (TextUtils.isEmpty(cummiuntyname)){
                    ToastManage.s(AddCarInfoActivity.this,"请先选择您所在的小区");
                    return;
                }
                String phonenumber = tvPhoneNumber.getText().toString();
                if (TextUtils.isEmpty(phonenumber)){
                    ToastManage.s(AddCarInfoActivity.this,"请先填写您的车牌号");
                    return;
                }
                //int id=getIntent().getIntExtra("comm_id",-1);
                HashMap<String, String> map = new HashMap<>();
                map.put("token",getToken());
                map.put("comm_id",communityId+"");
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
                net(true, false).post(3, Api.HousesList_URL, map);

            }
        });

        ll_add_one.setOnClickListener(new View.OnClickListener() {
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

        ll_add_two.setOnClickListener(new View.OnClickListener() {
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

                upLoad(path,1);
//                iconOne = path;

//                getUpdateImagePath(iconAddOne,requestCode);

            }else if (requestCode == 2){

                iconAddTwo.setImageURI(uri);

                upLoad(path,2);
//                iconTwo = path;
//                getUpdateImagePath(iconAddTwo,requestCode);
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
            if(houseListBean!=null&&houseListBean.getData()!=null){
                if(houseListBean.getData().getList()!=null&&houseListBean.getData().getList().size()>0){
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
                            HouseListBean.DataListBean dataListBean = dataListBeans.get(index);
                            communityId = dataListBean.getId();
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
                }else{
                    ToastManage.s(this,"很抱歉！您当前还未认证任何小区！");
                }
            }
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
            if("200".equals(addCarBean)){
                finish();
            }
            Toast.makeText(AddCarInfoActivity.this,addCarBean.getMessage().toString(),Toast.LENGTH_SHORT).show();

        }

    }


    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private void upLoad(String path,final  int type) {
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
                                            Toast.makeText(AddCarInfoActivity.this, "失败", Toast.LENGTH_SHORT).show();
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
                                                if(type==1)
                                                iconOne = uploadImgBean.getData().getImgUrl();
                                                else
                                                iconTwo = uploadImgBean.getData().getImgUrl();


                                                JSONObject jsonObject = new JSONObject(json);
                                                JSONObject jsonObject1 = jsonObject.optJSONObject("message");
                                                if (jsonObject1 != null) {
                                                    String message = jsonObject1.optString("message");
                                                    int code = jsonObject1.optInt("error_code");
                                                    if (0 == code) {

                                                        finish();
                                                    }
                                                    Toast.makeText(AddCarInfoActivity.this, message + "", Toast.LENGTH_SHORT).show();
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
