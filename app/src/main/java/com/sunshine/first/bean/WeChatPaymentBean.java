package com.sunshine.first.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author wavewave
 * @CreateDate: 2019-11-01 20:54
 * @Description: 微信支付bean
 * @Version: 1.0
 */
public class WeChatPaymentBean {


    /**
     * success : false
     * error_code : error
     * message : 成功
     * data : {"data":{"id":"36","type":"1","token":"1b5290be2aa33f40038ea54b6b074cb9","s":"/api/admin/wechat","appid":"wxc84f952138a6dd92","prepay_id":"wx0120535049139952f10cbc831435106200","partnerid":"1557691961","package":"Sign=WXPay","timestamp":1572612830,"noncestr":"ce9bab6274a6bd865728e050d1f2217c","sign":"E71F989CAD913114869B8267A0F0FC2D","packages":"Sign=WXPay","code":1}}
     */

    private boolean success;
    private String error_code;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : {"id":"36","type":"1","token":"1b5290be2aa33f40038ea54b6b074cb9","s":"/api/admin/wechat","appid":"wxc84f952138a6dd92","prepay_id":"wx0120535049139952f10cbc831435106200","partnerid":"1557691961","package":"Sign=WXPay","timestamp":1572612830,"noncestr":"ce9bab6274a6bd865728e050d1f2217c","sign":"E71F989CAD913114869B8267A0F0FC2D","packages":"Sign=WXPay","code":1}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 36
             * type : 1
             * token : 1b5290be2aa33f40038ea54b6b074cb9
             * s : /api/admin/wechat
             * appid : wxc84f952138a6dd92
             * prepay_id : wx0120535049139952f10cbc831435106200
             * partnerid : 1557691961
             * package : Sign=WXPay
             * timestamp : 1572612830
             * noncestr : ce9bab6274a6bd865728e050d1f2217c
             * sign : E71F989CAD913114869B8267A0F0FC2D
             * packages : Sign=WXPay
             * code : 1
             */

            private String id;
            private String type;
            private String token;
            private String s;
            private String appid;
            private String prepayid;
            private String partnerid;
            @SerializedName("package")
            private String packageX;
            private String timestamp;
            private String noncestr;
            private String sign;
            private String packages;
            private int code;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getS() {
                return s;
            }

            public void setS(String s) {
                this.s = s;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getPrepay_id() {
                return prepayid;
            }

            public void setPrepay_id(String prepay_id) {
                this.prepayid = prepay_id;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPackages() {
                return packages;
            }

            public void setPackages(String packages) {
                this.packages = packages;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }
        }
    }
}
