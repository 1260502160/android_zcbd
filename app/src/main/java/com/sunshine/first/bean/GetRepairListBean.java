package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class GetRepairListBean implements Serializable{

    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"total":7,"totalPage":1,"list":[{"id":8,"pay_state":"待受理","explain":"123","img_url":"http://47.93.50.2241553","repair_time":"2019-10-25 00:00"},{"id":9,"pay_state":"待受理","explain":"123","img_url":"http://47.93.50.2241553","repair_time":"2019-10-25 00:00"},{"id":11,"pay_state":"待受理","explain":"回来了","img_url":"http://47.93.50.224http://47.93.50.224","repair_time":"2019-10-24 00:00"},{"id":12,"pay_state":"待受理","explain":"透露","img_url":"http://47.93.50.224http://47.93.50.224","repair_time":"2019-10-24 00:00"},{"id":13,"pay_state":"待受理","explain":"利民","img_url":"http://47.93.50.224http://47.93.50.224","repair_time":"2019-10-24 00:00"},{"id":14,"pay_state":"待受理","explain":"呼噜","img_url":"http://47.93.50.224http://47.93.50.224","repair_time":"2019-10-24 00:00"},{"id":10,"pay_state":"待受理","explain":"123","img_url":"http://47.93.50.22412344567","repair_time":"2019-03-12 00:00"}]}
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
         * total : 7
         * totalPage : 1
         * list : [{"id":8,"pay_state":"待受理","explain":"123","img_url":"http://47.93.50.2241553","repair_time":"2019-10-25 00:00"},{"id":9,"pay_state":"待受理","explain":"123","img_url":"http://47.93.50.2241553","repair_time":"2019-10-25 00:00"},{"id":11,"pay_state":"待受理","explain":"回来了","img_url":"http://47.93.50.224http://47.93.50.224","repair_time":"2019-10-24 00:00"},{"id":12,"pay_state":"待受理","explain":"透露","img_url":"http://47.93.50.224http://47.93.50.224","repair_time":"2019-10-24 00:00"},{"id":13,"pay_state":"待受理","explain":"利民","img_url":"http://47.93.50.224http://47.93.50.224","repair_time":"2019-10-24 00:00"},{"id":14,"pay_state":"待受理","explain":"呼噜","img_url":"http://47.93.50.224http://47.93.50.224","repair_time":"2019-10-24 00:00"},{"id":10,"pay_state":"待受理","explain":"123","img_url":"http://47.93.50.22412344567","repair_time":"2019-03-12 00:00"}]
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
             * id : 8
             * pay_state : 待受理
             * explain : 123
             * img_url : http://47.93.50.2241553
             * repair_time : 2019-10-25 00:00
             */

            private int id;
            private String pay_state;
            private String explain;
            private String img_url;
            private String repair_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPay_state() {
                return pay_state;
            }

            public void setPay_state(String pay_state) {
                this.pay_state = pay_state;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getRepair_time() {
                return repair_time;
            }

            public void setRepair_time(String repair_time) {
                this.repair_time = repair_time;
            }
        }
    }
}
