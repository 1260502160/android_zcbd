package com.sunshine.first.bean;

import java.util.List;

/**
 * 获取商品详情bean
 */
public class MerchantShopBean {
    public boolean success;
    public String error_code;
    public String message;
    public MerchantShopDate data;

    public static class MerchantShopDate {
        public int id;//商品id

        public List<String> goods_images;//商品图片

        public String goods_describe;//商品描述

        public String wholesale_price;//批发价

        public String retail_price;//批发数量

        public int m_id;//零售价

        public List<String> goods_details_image;//商品详情图片

        public int wholesale_num;
        public String shop_name;//商户名称

        public String shop_img;//商户logo

        public String mobile;//客服电话

        public int goods_num;//商品数量

        public List<GoodsListBean.DataBean.ListBean> list;

    }

//    public static class ListBean {
//        public int id;//商品id
//
//        public String goods_image;//商品图片
//
//        public String goods_describe;//商品描述
//
//        public String wholesale_price;//批发价
//
//        public String retail_price;//零售价
//
//    }
}
