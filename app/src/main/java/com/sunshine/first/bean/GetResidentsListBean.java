package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class GetResidentsListBean implements Serializable{


    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : [{"id":18,"residents_name":"赵六","residents_mobile":"15232116969","face_recognition":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=440111053,1633811654&fm=26&gp=0.jpg","type":1,"status":1},{"id":16,"residents_name":"钱五","residents_mobile":"15232116969","face_recognition":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=440111053,1633811654&fm=26&gp=0.jpg","type":2,"status":1},{"id":17,"residents_name":"钱六","residents_mobile":"15232116969","face_recognition":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=440111053,1633811654&fm=26&gp=0.jpg","type":2,"status":1}]
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
         * id : 18
         * residents_name : 赵六
         * residents_mobile : 15232116969
         * face_recognition : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=440111053,1633811654&fm=26&gp=0.jpg
         * type : 1
         * status : 1
         */

        private int id;
        private String residents_name;
        private String residents_mobile;
        private String face_recognition;
        private int type;
        private int status;

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

        public String getFace_recognition() {
            return face_recognition;
        }

        public void setFace_recognition(String face_recognition) {
            this.face_recognition = face_recognition;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
