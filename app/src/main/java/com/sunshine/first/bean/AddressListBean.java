package com.sunshine.first.bean;

import java.util.List;

/**
 * 地址列表 bean
 */
public class AddressListBean {
    public boolean success;
    public String error_code;
    public String message;
    public List<AddressBean> data;

    public static class AddressBean {
        public int id;//地址id
        public String name;//姓名
        public String mobile;//电话
        public String detail;//详细地址
        public String province_name;//省名称
        public String city_name;//市名称
        public String area_name;//区名称

    }

}
