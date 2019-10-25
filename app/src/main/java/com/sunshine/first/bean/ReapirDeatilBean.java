package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class ReapirDeatilBean implements Serializable{

    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"id":1,"order_number":"123456","name":"王洋","mobile":"15652591806","houses_number_name":"206","explain":"测试报修描述","imgs":["http://47.93.50.224/storage/xier/cb28e475922b4cd4afe7c26e392ec1531215.jpg"],"repair_time":"2019-10-02 07:13","repair_people":"测试111···","repair_mobile":"12345678901"}
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
         * order_number : 123456
         * name : 王洋
         * mobile : 15652591806
         * houses_number_name : 206
         * explain : 测试报修描述
         * imgs : ["http://47.93.50.224/storage/xier/cb28e475922b4cd4afe7c26e392ec1531215.jpg"]
         * repair_time : 2019-10-02 07:13
         * repair_people : 测试111···
         * repair_mobile : 12345678901
         */

        private int id;
        private String order_number;
        private String name;
        private String mobile;
        private String houses_number_name;
        private String explain;
        private String repair_time;
        private String repair_people;
        private String repair_mobile;
        private List<String> imgs;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHouses_number_name() {
            return houses_number_name;
        }

        public void setHouses_number_name(String houses_number_name) {
            this.houses_number_name = houses_number_name;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getRepair_time() {
            return repair_time;
        }

        public void setRepair_time(String repair_time) {
            this.repair_time = repair_time;
        }

        public String getRepair_people() {
            return repair_people;
        }

        public void setRepair_people(String repair_people) {
            this.repair_people = repair_people;
        }

        public String getRepair_mobile() {
            return repair_mobile;
        }

        public void setRepair_mobile(String repair_mobile) {
            this.repair_mobile = repair_mobile;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
