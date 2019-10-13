package com.sunshine.first.bean;

import java.io.Serializable;

public class RegisterBean implements Serializable{


    /**
     * success : true
     * error_code : 200
     * message : 注册成功
     * data : {"token":"0fc7e4c77217b8afee2d9f2e735741f6","is_verify":0}
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
         * token : 0fc7e4c77217b8afee2d9f2e735741f6
         * is_verify : 0
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
