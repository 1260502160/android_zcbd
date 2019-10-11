package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class GetCommunityBean implements Serializable{

    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : [{"id":4,"name":"11111111111111111111"},{"id":11,"name":"11111111111111111111"},{"id":14,"name":"11111111111111"},{"id":15,"name":"阳光和赢"},{"id":16,"name":"海淀区温泉镇"},{"id":17,"name":"东1区"},{"id":49,"name":"枫林小区"},{"id":50,"name":"枫林小区"},{"id":51,"name":"枫林小区"},{"id":52,"name":"青年公寓"}]
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
         * id : 4
         * name : 11111111111111111111
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
