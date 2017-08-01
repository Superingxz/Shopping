package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-28.
 */

public class ComboBean {

    /**
     * event : 200
     * data : [{"goods_name":"农夫山泉桶装水","image_url":"20170522111851084002.jpg","goods_id":"61bdb660-3e9d-11e7-9367-b8975a6f72af","specification":"18.9L","company":"桶","id":"c11b0da0-3e9e-11e7-9d6e-b8975a6f72af"},{"goods_name":"怡宝桶装水","image_url":"20170522111913272003.jpg","goods_id":"6ea58ba0-3e9d-11e7-9367-b8975a6f72af","specification":"18.9L","company":"桶","id":"df0bb210-3e9e-11e7-9d6e-b8975a6f72af"}]
     * message : 数据加载成功
     * timestamp : 2017-07-28 14:32:12
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
         * goods_name : 农夫山泉桶装水
         * image_url : 20170522111851084002.jpg
         * goods_id : 61bdb660-3e9d-11e7-9367-b8975a6f72af
         * specification : 18.9L
         * company : 桶
         * id : c11b0da0-3e9e-11e7-9d6e-b8975a6f72af
         */

        private String goods_name;
        private String image_url;
        private String goods_id;
        private String specification;
        private String company;
        private String id;
        private String storeID;
        private String addressID;

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getStoreID() {
            return storeID;
        }

        public void setStoreID(String storeID) {
            this.storeID = storeID;
        }

        public String getAddressID() {
            return addressID;
        }

        public void setAddressID(String addressID) {
            this.addressID = addressID;
        }
    }
}
