package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class HouseListBean implements Serializable{

    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : [{"id":37,"community_id":1,"status":3,"type":2,"houses_number_name":"101","floors_name":"1层","unitdoor_name":"5单元","building_name":"5号楼","community_name":"东1区"}]
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
         * id : 37
         * community_id : 1
         * status : 3
         * type : 2
         * houses_number_name : 101
         * floors_name : 1层
         * unitdoor_name : 5单元
         * building_name : 5号楼
         * community_name : 东1区
         */

        private int id;
        private int community_id;
        private int status;
        private int type;
        private String houses_number_name;
        private String floors_name;
        private String unitdoor_name;
        private String building_name;
        private String community_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCommunity_id() {
            return community_id;
        }

        public void setCommunity_id(int community_id) {
            this.community_id = community_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getHouses_number_name() {
            return houses_number_name;
        }

        public void setHouses_number_name(String houses_number_name) {
            this.houses_number_name = houses_number_name;
        }

        public String getFloors_name() {
            return floors_name;
        }

        public void setFloors_name(String floors_name) {
            this.floors_name = floors_name;
        }

        public String getUnitdoor_name() {
            return unitdoor_name;
        }

        public void setUnitdoor_name(String unitdoor_name) {
            this.unitdoor_name = unitdoor_name;
        }

        public String getBuilding_name() {
            return building_name;
        }

        public void setBuilding_name(String building_name) {
            this.building_name = building_name;
        }

        public String getCommunity_name() {
            return community_name;
        }

        public void setCommunity_name(String community_name) {
            this.community_name = community_name;
        }
    }
}
