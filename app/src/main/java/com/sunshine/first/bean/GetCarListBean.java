package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class GetCarListBean implements Serializable{


    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : [{"id":1,"plate_num":"京A123456","community_name":"海淀区温泉镇"}]
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
         * plate_num : 京A123456
         * community_name : 海淀区温泉镇
         */

        private int id;
        private String plate_num;
        private String community_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlate_num() {
            return plate_num;
        }

        public void setPlate_num(String plate_num) {
            this.plate_num = plate_num;
        }

        public String getCommunity_name() {
            return community_name;
        }

        public void setCommunity_name(String community_name) {
            this.community_name = community_name;
        }
    }
}
