package com.sunshine.first.bean;

import java.io.Serializable;

public class LoginBean implements Serializable{


    /**
     * success : true
     * error_code : 200
     * message : 登录成功
     * data : {"token":"acbbf1aa987a9353c5d7cf9fdc2a0fd1","is_verify":3}
     */

    private boolean success;
    private String error_code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : acbbf1aa987a9353c5d7cf9fdc2a0fd1
         * is_verify : 3
         */

        private String token;
        private int is_verify;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(int is_verify) {
            this.is_verify = is_verify;
        }
    }
}
