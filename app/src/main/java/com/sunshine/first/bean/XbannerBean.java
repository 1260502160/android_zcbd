package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class XbannerBean implements Serializable{

    /**
     * success : true
     * error_code : 200
     * message :
     * data : [{"url":"测试url2","begin_time":1569392586,"end_time":1572432728,"jump_url":"跳转地址2"},{"url":"测试url3","begin_time":1569392586,"end_time":1572432728,"jump_url":"跳转地址3"}]
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
         * url : 测试url2
         * begin_time : 1569392586
         * end_time : 1572432728
         * jump_url : 跳转地址2
         */

        private String url;
        private int begin_time;
        private int end_time;
        private String jump_url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(int begin_time) {
            this.begin_time = begin_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }
    }
}
