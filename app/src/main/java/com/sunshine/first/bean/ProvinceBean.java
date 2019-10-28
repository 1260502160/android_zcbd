package com.sunshine.first.bean;

import java.util.List;

/**
 * 省市区类
 */
public class ProvinceBean {
    public boolean success;
    public String error_code;
    public String message;
    public List<ProvinceArrayBean> data;

    public static class ProvinceArrayBean {

        public int id;//省市区id

        public String name;//省市区名称
    }
}
