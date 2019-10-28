package com.sunshine.first.bean;

import java.io.Serializable;

public class GetResidentsDetailBean implements Serializable{

    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"id":2,"residents_name":"张四","residents_mobile":"15236554686","sex":0,"identity_card_number":"350583194509012634","type":5}
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
         * id : 2
         * residents_name : 张四
         * residents_mobile : 15236554686
         * sex : 0
         * identity_card_number : 350583194509012634
         * type : 5
         */

        private int id;
        private String residents_name;
        private String residents_mobile;
        private int sex;
        private String identity_card_number;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getResidents_name() {
            return residents_name;
        }

        public void setResidents_name(String residents_name) {
            this.residents_name = residents_name;
        }

        public String getResidents_mobile() {
            return residents_mobile;
        }

        public void setResidents_mobile(String residents_mobile) {
            this.residents_mobile = residents_mobile;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getIdentity_card_number() {
            return identity_card_number;
        }

        public void setIdentity_card_number(String identity_card_number) {
            this.identity_card_number = identity_card_number;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
