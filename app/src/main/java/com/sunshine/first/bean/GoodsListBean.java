package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsListBean implements Serializable{

    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"total":3,"totalPage":1,"list":[{"id":11,"goods_image":"php.jf.com2","goods_describe":"1234","wholesale_price":"4.00","retail_price":"2.00"},{"id":10,"goods_image":"php.jf.com2","goods_describe":"33","wholesale_price":"2.00","retail_price":"2.00"},{"id":4,"goods_image":"php.jf.com5","goods_describe":"1","wholesale_price":"5.00","retail_price":"5.00"}]}
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
         * total : 3
         * totalPage : 1
         * list : [{"id":11,"goods_image":"php.jf.com2","goods_describe":"1234","wholesale_price":"4.00","retail_price":"2.00"},{"id":10,"goods_image":"php.jf.com2","goods_describe":"33","wholesale_price":"2.00","retail_price":"2.00"},{"id":4,"goods_image":"php.jf.com5","goods_describe":"1","wholesale_price":"5.00","retail_price":"5.00"}]
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
             * id : 11
             * goods_image : php.jf.com2
             * goods_describe : 1234
             * wholesale_price : 4.00
             * retail_price : 2.00
             */

            private int id;
            private String goods_image;
            private String goods_describe;
            private String wholesale_price;
            private String retail_price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getWholesale_price() {
                return wholesale_price;
            }

            public void setWholesale_price(String wholesale_price) {
                this.wholesale_price = wholesale_price;
            }

            public String getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(String retail_price) {
                this.retail_price = retail_price;
            }
        }
    }
}
