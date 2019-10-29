package com.abner.ming.base.model;

/**
 * author:AbnerMing
 * date:2019/6/7
 */
public class Api {
    //接口
//    public final static  String BASE_URL = "http://47.93.50.224/api/app/";
    public final static  String BASE_URL = "http://zc.zhongchengbd.com/api/app/";
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
    //获取商品分类
    public final static  String GetClaList_URL = BASE_URL+"getClaList";
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
    //获取访客授权记录列表
    public final static  String VisitorRecord_URL = BASE_URL+"getAppVisitorList";
    //获取住户列表
    public final static  String GetResidentsList_URL = BASE_URL+"getResidentsList";
    //添加意见反馈
    public final static  String AddFeedback_URL = BASE_URL+"addFeedback";
    //解除绑定手机号 更换手机号
    public final static String UPDATE_MOBILE_URL = BASE_URL + "updateMobile";
    //添加报修
    public final static String AddRepair_URL = BASE_URL + "addRepair";
    //报修详情
    public final static String GetRepairDetail_URL = BASE_URL + "getRepairDetail";
    //取消报修
    public final static String CancelRepair_URL = BASE_URL + "cancelRepair";
    //报修列表
    public final static String GetRepairList_URL = BASE_URL + "getRepairList";
    //获取商家店铺详情
    public final static String GetShopInfoURL = BASE_URL + "getShopInfo";
    //获取收货列表
    public final static String GetAddressListURL = BASE_URL + "getAddressList";
    //添加收货地址
    public final static String ADD_AddressListURL = BASE_URL + "addAddress";
    //获取收货地址详情
    public final static String GetAddressDetailURL = BASE_URL + "getAddressDetail";
    //添加修改车辆///
    public final static String AddCar_URL = BASE_URL + "addCar";
    //获取车辆详情///
    public final static String GetCarDetail_URL = BASE_URL + "getCarDetail";
    //我的订单列表
    public final static String GetGoodsOrderList_URL = BASE_URL + "getGoodsOrderList";
    //获取住户详情
    public final static String GetResidentsDetail_URL = BASE_URL + "getResidentsDetail";
    //app生成固定停车订单
    public final static String ParkingCharge_URL = BASE_URL + "parkingCharge";
    //app生成临时停车订单
    public final static String FindParkInfo_URL = BASE_URL + "findParkInfo";
    //获取省市区 id	-0为获取所有省
    public final static String GetCityList_URL = BASE_URL + "getCityList";

    //添加住户
    public  final static String Add_Residents = BASE_URL+"addResidents";
    //删除住户
    public  final static String Del_Residents = BASE_URL+"delResidents";
    //审核住户
    public  final static String Owner_Check = BASE_URL+"ownerCheck";
    //生成商品订单接口
    public final static String CreateGoodsOrder_URL = BASE_URL + "createGoodsOrder";
}
