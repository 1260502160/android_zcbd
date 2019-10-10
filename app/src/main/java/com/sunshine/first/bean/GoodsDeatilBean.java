package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsDeatilBean implements Serializable{


    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"id":10,"goods_images":["php.jf.com2"],"goods_describe":"33","wholesale_price":"2.00","retail_price":"2.00","m_id":2,"goods_details_image":["php.jf.com2"],"wholesale_num":2,"shop_name":"测试商家","shop_img":"php.jf.com","mobile":"","goods_num":2,"list":[{"id":11,"goods_image":"php.jf.com2","goods_describe":"1234","wholesale_price":"4.00","retail_price":"2.00"},{"id":10,"goods_image":"php.jf.com2","goods_describe":"33","wholesale_price":"2.00","retail_price":"2.00"}]}
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
         * id : 10
         * goods_images : ["php.jf.com2"]
         * goods_describe : 33
         * wholesale_price : 2.00
         * retail_price : 2.00
         * m_id : 2
         * goods_details_image : ["php.jf.com2"]
         * wholesale_num : 2
         * shop_name : 测试商家
         * shop_img : php.jf.com
         * mobile :
         * goods_num : 2
         * list : [{"id":11,"goods_image":"php.jf.com2","goods_describe":"1234","wholesale_price":"4.00","retail_price":"2.00"},{"id":10,"goods_image":"php.jf.com2","goods_describe":"33","wholesale_price":"2.00","retail_price":"2.00"}]
         */

        private int id;
        private String goods_describe;
        private String wholesale_price;
        private String retail_price;
        private int m_id;
        private int wholesale_num;
        private String shop_name;
        private String shop_img;
        private String mobile;
        private int goods_num;
        private List<String> goods_images;
        private List<String> goods_details_image;
        private List<ListBean> list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getM_id() {
            return m_id;
        }

        public void setM_id(int m_id) {
            this.m_id = m_id;
        }

        public int getWholesale_num() {
            return wholesale_num;
        }

        public void setWholesale_num(int wholesale_num) {
            this.wholesale_num = wholesale_num;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_img() {
            return shop_img;
        }

        public void setShop_img(String shop_img) {
            this.shop_img = shop_img;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public List<String> getGoods_images() {
            return goods_images;
        }

        public void setGoods_images(List<String> goods_images) {
            this.goods_images = goods_images;
        }

        public List<String> getGoods_details_image() {
            return goods_details_image;
        }

        public void setGoods_details_image(List<String> goods_details_image) {
            this.goods_details_image = goods_details_image;
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
