package com.sunshine.first.bean;

import java.io.Serializable;

public class GetCarDetail implements Serializable{

    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"id":1,"comm_id":16,"plate_num":"京A25316","license":"http://47.93.50.224./1jpg","car_photo":"http://47.93.50.224./1jpg"}
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
         * id : 1
         * comm_id : 16
         * plate_num : 京A25316
         * license : http://47.93.50.224./1jpg
         * car_photo : http://47.93.50.224./1jpg
         */

        private int id;
        private int comm_id;
        private String plate_num;
        private String license;
        private String car_photo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getComm_id() {
            return comm_id;
        }

        public void setComm_id(int comm_id) {
            this.comm_id = comm_id;
        }

        public String getPlate_num() {
            return plate_num;
        }

        public void setPlate_num(String plate_num) {
            this.plate_num = plate_num;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getCar_photo() {
            return car_photo;
        }

        public void setCar_photo(String car_photo) {
            this.car_photo = car_photo;
        }
    }
}
