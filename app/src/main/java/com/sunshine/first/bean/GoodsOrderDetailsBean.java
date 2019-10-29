package com.sunshine.first.bean;

/**
 * 获取订单详情
 */
public class GoodsOrderDetailsBean {

    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"id":13,"goods_id":11,"address_name":"李四","address_mobile":"152321165452","address":"北京北京市昌平区沙河1","goods_image":"php.jf.com2","goods_describe":"1234","money":"2.00","goods_num":10,"order_no":"2019092517291852927","created_at":"2019年09月25日 17:45","order_money":"20.00","pay_type":0,"pay_time":0,"express_company_id":"0","express_number":"0","refund_apply_time":"2019年09月26日 09:59","refund_com_time":0,"close_time":0,"refund_status":0,"order_status":"已关闭","order_status_type":4,"goods_status":1,"note":"","pay_end_time":0}
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
         * id : 13
         * goods_id : 11
         * address_name : 李四
         * address_mobile : 152321165452
         * address : 北京北京市昌平区沙河1
         * goods_image : php.jf.com2
         * goods_describe : 1234
         * money : 2.00
         * goods_num : 10
         * order_no : 2019092517291852927
         * created_at : 2019年09月25日 17:45
         * order_money : 20.00
         * pay_type : 0
         * pay_time : 0
         * express_company_id : 0
         * express_number : 0
         * refund_apply_time : 2019年09月26日 09:59
         * refund_com_time : 0
         * close_time : 0
         * refund_status : 0
         * order_status : 已关闭
         * order_status_type : 4
         * goods_status : 1
         * note :
         * pay_end_time : 0
         */

        private int id;
        private int goods_id;
        private String address_name;
        private String address_mobile;
        private String address;
        private String goods_image;
        private String goods_describe;
        private String money;
        private int goods_num;
        private String order_no;
        private String created_at;
        private String order_money;
        private int pay_type;
        private int pay_time;
        private String express_company_id;
        private String express_number;
        private String refund_apply_time;
        private int refund_com_time;
        private String close_time;
        private int refund_status;
        private String order_status;
        private int order_status_type;
        private int goods_status;
        private String note;
        private int pay_end_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getAddress_name() {
            return address_name;
        }

        public void setAddress_name(String address_name) {
            this.address_name = address_name;
        }

        public String getAddress_mobile() {
            return address_mobile;
        }

        public void setAddress_mobile(String address_mobile) {
            this.address_mobile = address_mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public String getGoods_describe() {
            return goods_describe;
        }

        public void setGoods_describe(String goods_describe) {
            this.goods_describe = goods_describe;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getOrder_money() {
            return order_money;
        }

        public void setOrder_money(String order_money) {
            this.order_money = order_money;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public int getPay_time() {
            return pay_time;
        }

        public void setPay_time(int pay_time) {
            this.pay_time = pay_time;
        }

        public String getExpress_company_id() {
            return express_company_id;
        }

        public void setExpress_company_id(String express_company_id) {
            this.express_company_id = express_company_id;
        }

        public String getExpress_number() {
            return express_number;
        }

        public void setExpress_number(String express_number) {
            this.express_number = express_number;
        }

        public String getRefund_apply_time() {
            return refund_apply_time;
        }

        public void setRefund_apply_time(String refund_apply_time) {
            this.refund_apply_time = refund_apply_time;
        }

        public int getRefund_com_time() {
            return refund_com_time;
        }

        public void setRefund_com_time(int refund_com_time) {
            this.refund_com_time = refund_com_time;
        }

        public String getClose_time() {
            return close_time;
        }

        public void setClose_time(String close_time) {
            this.close_time = close_time;
        }

        public int getRefund_status() {
            return refund_status;
        }

        public void setRefund_status(int refund_status) {
            this.refund_status = refund_status;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public int getOrder_status_type() {
            return order_status_type;
        }

        public void setOrder_status_type(int order_status_type) {
            this.order_status_type = order_status_type;
        }

        public int getGoods_status() {
            return goods_status;
        }

        public void setGoods_status(int goods_status) {
            this.goods_status = goods_status;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getPay_end_time() {
            return pay_end_time;
        }

        public void setPay_end_time(int pay_end_time) {
            this.pay_end_time = pay_end_time;
        }
    }
}
