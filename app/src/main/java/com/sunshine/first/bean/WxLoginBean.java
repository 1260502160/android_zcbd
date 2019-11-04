package com.sunshine.first.bean;

/**
 * @author dell
 * @CreateDate: 2019-11-04 15:28
 * @Description:
 * @Version: 1.0
 */
public class WxLoginBean {
    public boolean success;
    public String error_code;
    public String message;
    public WxLoginBean.DataBean data;


    public class DataBean {
        public String token;
        public int type;
        public int is_verify;
    }
}
