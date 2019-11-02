package com.sunshine.first.bean;

/**
 * @author wavewave
 * @CreateDate: 2019-11-02 19:30
 * @Description: 缴费详情
 * @Version: 1.0
 */
public class PayDetailsBean {

    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"order_id":"2019091708363720729","pay_type":"微信","pay_time":"0000-00-00 00:00:00","property_fee":"112.35","houses_number_name":"127","floors_name":"1层","unitdoor_name":"1单元","building_name":"1号楼","community_name":"海淀区温泉镇","type":"物业费"}
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
         * order_id : 2019091708363720729
         * pay_type : 微信
         * pay_time : 0000-00-00 00:00:00
         * property_fee : 112.35
         * houses_number_name : 127
         * floors_name : 1层
         * unitdoor_name : 1单元
         * building_name : 1号楼
         * community_name : 海淀区温泉镇
         * type : 物业费
         */

        private String order_id;
        private String pay_type;
        private String pay_time;
        private String property_fee;
        private String houses_number_name;
        private String floors_name;
        private String unitdoor_name;
        private String building_name;
        private String community_name;
        private String type;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getProperty_fee() {
            return property_fee;
        }

        public void setProperty_fee(String property_fee) {
            this.property_fee = property_fee;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
