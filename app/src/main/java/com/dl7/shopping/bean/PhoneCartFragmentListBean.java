package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-28.
 */

public class PhoneCartFragmentListBean {

    /**
     * event : 200
     * data : [{"store_id":"c6590bb0-614d-11e7-b74a-bc5ff4fd8413","original_price":180000,"goods_id":"be4af0b0-5c79-11e7-b152-00e066f08ac3","discount":4000,"card_type":"UNICOM","describes":"每月3GB国内流量600分钟国内语音","spec":12,"present_price":120000,"province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","storeName":"测试专用","details":"<p><span style=\"color: rgb(103, 106, 108); font-family: &quot;open sans&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; background-color: rgb(245, 245, 245);\">每月3GB国内流量600分钟国内语音超出部分流量按1M/1元计算<\/span><\/p>","id":"c685d310-6234-11e7-9a48-00e066f08ac3","goodsName":"1200元基本套餐","country_id":"a0135380-0d2d-11e7-a212-b8975a6f72af","city_id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","status":"ENABLE"}]
     * message : 加载成功
     * timestamp : 2017-07-28 17:21:51
     */

    private String event;
    private String message;
    private String timestamp;
    private List<DataBean> data;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * store_id : c6590bb0-614d-11e7-b74a-bc5ff4fd8413
         * original_price : 180000
         * goods_id : be4af0b0-5c79-11e7-b152-00e066f08ac3
         * discount : 4000
         * card_type : UNICOM
         * describes : 每月3GB国内流量600分钟国内语音
         * spec : 12
         * present_price : 120000
         * province_id : ac097e20-0d1f-11e7-aa69-b8975a6f72af
         * storeName : 测试专用
         * details : <p><span style="color: rgb(103, 106, 108); font-family: &quot;open sans&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; background-color: rgb(245, 245, 245);">每月3GB国内流量600分钟国内语音超出部分流量按1M/1元计算</span></p>
         * id : c685d310-6234-11e7-9a48-00e066f08ac3
         * goodsName : 1200元基本套餐
         * country_id : a0135380-0d2d-11e7-a212-b8975a6f72af
         * city_id : e01f3ba0-0d1f-11e7-aa69-b8975a6f72af
         * status : ENABLE
         */

        private String store_id;
        private int original_price;
        private String goods_id;
        private int discount;
        private String card_type;
        private String describes;
        private int spec;
        private int present_price;
        private String province_id;
        private String storeName;
        private String details;
        private String id;
        private String goodsName;
        private String country_id;
        private String city_id;
        private String status;

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public int getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(int original_price) {
            this.original_price = original_price;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getDescribes() {
            return describes;
        }

        public void setDescribes(String describes) {
            this.describes = describes;
        }

        public int getSpec() {
            return spec;
        }

        public void setSpec(int spec) {
            this.spec = spec;
        }

        public int getPresent_price() {
            return present_price;
        }

        public void setPresent_price(int present_price) {
            this.present_price = present_price;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
