package com.sunshine.first.bean;

import java.util.List;

/**
 * 获取商品分类列表
 */
public class OnLineBean {

    private boolean success;
    private String error_code;
    private String message;
    private List<OnLineDataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OnLineDataBean> getData() {
        return data;
    }

    public void setData(List<OnLineDataBean> data) {
        this.data = data;
    }

    public static class OnLineDataBean {
        private int id;//分类id
        private String name;//分类名称

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
