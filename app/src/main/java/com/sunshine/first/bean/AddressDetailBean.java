package com.sunshine.first.bean;

/**
 * 获取收货地址详情 bean
 */
public class AddressDetailBean {

    public boolean success;
    public String error_code;
    public String message;
    public AddressDetailListBean data;

    public static class AddressDetailListBean {
        public int id;//地址id
        public String name;//收货人姓名
        public String mobile;//手机号
        public String detail;//详细地址
        public int province_id;//省id
        public int city_id;//市id
        public int area_id;//区id
        public String province_name;//省名称
        public String city_name;//市名称
        public String area_name;//区名称


    }
}
