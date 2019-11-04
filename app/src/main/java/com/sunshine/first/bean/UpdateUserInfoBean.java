package com.sunshine.first.bean;

import java.io.Serializable;

public class UpdateUserInfoBean implements Serializable{


    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"id":28,"mobile":"18110023262","nickname":"","photo":"http://zc.zhongchengbd.com/storage/xier/6a19370e85c3ff3a43e49331565029416620.jpeg","is_verify":0}
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
         * id : 28
         * mobile : 18110023262
         * nickname :
         * photo : http://zc.zhongchengbd.com/storage/xier/6a19370e85c3ff3a43e49331565029416620.jpeg
         * is_verify : 0
         */

        private int id;
        private String mobile;
        private String nickname;
        private String photo;
        private int is_verify;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(int is_verify) {
            this.is_verify = is_verify;
        }
    }
}
