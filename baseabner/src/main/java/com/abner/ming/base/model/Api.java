package com.abner.ming.base.model;

/**
 * author:AbnerMing
 * date:2019/6/7
 */
public class Api {
    //接口
    public final static  String BASE_URL = "http://47.93.50.224/api/app/";
    //登录
    public final static  String Login_URL = BASE_URL+"login";
    //发送验证码
    public final static  String SendSms_URL = BASE_URL+"sendSms";
    //忘记密码
    public final static  String RePwd_URL = BASE_URL+"rePwd";
    //注册
    public final static  String Register_URL = BASE_URL+"reg";
    //banner
    public final static  String Xbanner_URL = BASE_URL+"bannerList";
    //首页商品列表
    public final static  String GoodsList_URL = BASE_URL+"getGoodsList";
    //首页商品详情
    public final static  String GoodsListDeatil_URL = BASE_URL+"getGoodsDetail";
    //获取个人信息
    public final static  String GetUserInfo_URL = BASE_URL+"getUserInfo";
    //获取小区
    public final static  String GetHosing_URL = BASE_URL+"getCommunity";
    //房屋认证
    public final static  String OwnerVerify_URL = BASE_URL+"ownerVerify";
    //获取车辆列表
    public final static  String GetCarList_URL = BASE_URL+"getCarList";
    //上传图片
    public final static  String UploadImg = BASE_URL+"uploadImg";
    //修改头像,昵称
    public final static  String UpdateUserInfo_URL = BASE_URL+"updateUserInfo";
    //获取房屋列表
    public final static  String HousesList_URL = BASE_URL+"housesList";
    //访客登记
    public final static  String VisitorAdd_URL = BASE_URL+"visitorAdd";
}
