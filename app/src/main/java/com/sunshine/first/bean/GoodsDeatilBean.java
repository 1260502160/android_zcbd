package com.sunshine.first.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 商品详情页面
 */
public class GoodsDeatilBean implements Serializable {


    /**
     * success : true
     * error_code : 200
     * message : 获取成功
     * data : {"id":10,"goods_images":["php.jf.com2"],"goods_describe":"33","wholesale_price":"2.00","retail_price":"2.00","m_id":2,"goods_details_image":["php.jf.com2"],"wholesale_num":2,"shop_name":"测试商家","shop_img":"php.jf.com","mobile":"","goods_num":2,"list":[{"id":11,"goods_image":"php.jf.com2","goods_describe":"1234","wholesale_price":"4.00","retail_price":"2.00"},{"id":10,"goods_image":"php.jf.com2","goods_describe":"33","wholesale_price":"2.00","retail_price":"2.00"}]}
     */

    public boolean success;
    public String error_code;
    public String message;
    public DataBean data;

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

        public int id;//商品id
        public List<String> goods_images;//商品图片
        public String goods_describe;//商品描述
        public String wholesale_price;//批发价
        public int wholesale_num;//批发数量
        public String retail_price;//零售价
        public int m_id;//商户id
        public List<String> goods_details_image;//商品详情图片
        public String shop_name;//商户名称
        public String shop_img;//商户logo
        public String mobile;//客服电话
        public int goods_num;//商品数量
        public List<GoodsListBean.DataBean.ListBean> list;

//
//        public static class ListBean {
//            /**
//             * id : 11
//             * goods_image : php.jf.com2
//             * goods_describe : 1234
//             * wholesale_price : 4.00
//             * retail_price : 2.00
//             */
//
//            public int id;//商品id
//            public String goods_image;//商品图片
//            public String goods_describe;//商品描述
//            public String wholesale_price;//批发价
//            public String retail_price;//零售价
//
//        }
    }
}
