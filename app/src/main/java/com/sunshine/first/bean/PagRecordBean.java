package com.sunshine.first.bean;

import java.util.List;

/**
 * 缴费记录列表 bean
 */
public class PagRecordBean {


    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"total":2,"totalPage":1,"list":[{"day":"09","list":[{"id":1,"created_at":"2019-09-20","money":"112.35","order_type":1},{"id":3,"created_at":"2019-09-01","money":"280.23","order_type":1}]},{"day":"08","list":[{"id":2,"created_at":"2019-08-13","money":"37.30","order_type":1}]}]}
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
         * list : [{"day":"09","list":[{"id":1,"created_at":"2019-09-20","money":"112.35","order_type":1},{"id":3,"created_at":"2019-09-01","money":"280.23","order_type":1}]},{"day":"08","list":[{"id":2,"created_at":"2019-08-13","money":"37.30","order_type":1}]}]
         */

        private int total;
        private int totalPage;
        private List<ListBeanX> list;

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

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX {
            /**
             * day : 09
             * list : [{"id":1,"created_at":"2019-09-20","money":"112.35","order_type":1},{"id":3,"created_at":"2019-09-01","money":"280.23","order_type":1}]
             */

            private String day;
            private List<ListBean> list;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 1
                 * created_at : 2019-09-20
                 * money : 112.35
                 * order_type : 1
                 */

                private int id;
                private String created_at;
                private String money;
                private int order_type;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public int getOrder_type() {
                    return order_type;
                }

                public void setOrder_type(int order_type) {
                    this.order_type = order_type;
                }
            }
        }
    }
}
