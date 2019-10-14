package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class LouCengBean implements Serializable{


    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : [{"id":1,"name":"1层"},{"id":2,"name":"3层"},{"id":3,"name":"4层"},{"id":4,"name":"4层"},{"id":5,"name":"2层"},{"id":6,"name":"5层"}]
     */

    private boolean success;
    private String error_code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 1层
         */

        private int id;
        private String name;

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
