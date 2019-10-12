package com.sunshine.first.bean;

import java.io.Serializable;

public class UploadImgBean implements Serializable{


    /**
     * success : true
     * error_code : 200
     * message : 上传成功
     * data : {"imgUrl":"47.93.50.224/storage/xier/c0eff79269d1543b8ecf664d8110a1c71780.png"}
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
         * imgUrl : 47.93.50.224/storage/xier/c0eff79269d1543b8ecf664d8110a1c71780.png
         */

        private String imgUrl;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
