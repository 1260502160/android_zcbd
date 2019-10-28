package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class AllIndentBean implements Serializable{


    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"total":2,"totalPage":1,"list":[{"id":12,"goods_id":11,"order_no":"2019092517150280472","order_status":"已关闭","created_at":"2019-09-25 19:45:29","goods_image":"php.jf.com2","goods_describe":"1234","money":"2.00","goods_num":10,"order_money":"20.00","pay_end_time":0},{"id":13,"goods_id":11,"order_no":"2019092517291852927","order_status":"已关闭","created_at":"2019-09-25 17:45:35","goods_image":"php.jf.com2","goods_describe":"1234","money":"2.00","goods_num":10,"order_money":"20.00","pay_end_time":0}]}
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
         * total : 2
         * totalPage : 1
         * list : [{"id":12,"goods_id":11,"order_no":"2019092517150280472","order_status":"已关闭","created_at":"2019-09-25 19:45:29","goods_image":"php.jf.com2","goods_describe":"1234","money":"2.00","goods_num":10,"order_money":"20.00","pay_end_time":0},{"id":13,"goods_id":11,"order_no":"2019092517291852927","order_status":"已关闭","created_at":"2019-09-25 17:45:35","goods_image":"php.jf.com2","goods_describe":"1234","money":"2.00","goods_num":10,"order_money":"20.00","pay_end_time":0}]
         */

        private int total;
        private int totalPage;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 12
             * goods_id : 11
             * order_no : 2019092517150280472
             * order_status : 已关闭
             * created_at : 2019-09-25 19:45:29
             * goods_image : php.jf.com2
             * goods_describe : 1234
             * money : 2.00
             * goods_num : 10
             * order_money : 20.00
             * pay_end_time : 0
             */

            private int id;
            private int goods_id;
            private String order_no;
            private String order_status;
            private String created_at;
            private String goods_image;
            private String goods_describe;
            private String money;
            private int goods_num;
            private String order_money;
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

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
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

            public String getOrder_money() {
                return order_money;
            }

            public void setOrder_money(String order_money) {
                this.order_money = order_money;
            }

            public int getPay_end_time() {
                return pay_end_time;
            }

            public void setPay_end_time(int pay_end_time) {
                this.pay_end_time = pay_end_time;
            }
        }
    }
}
