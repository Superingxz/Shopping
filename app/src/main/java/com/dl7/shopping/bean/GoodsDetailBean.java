package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-21.
 */

public class GoodsDetailBean {

    /**
     * event : 200
     * data : {"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","goods_name":"农夫山泉桶装水","images":[{"image_url":"20170522112435976007.jpg","id":"c11b0da0-3e9e-11e7-9d6e-b8975a6f72af"}],"present_price":10000,"category_id":"e1875240-095d-11e7-a5d5-b8975a6f72af","is_reclaim":"RETURN","goods_id":"61bdb660-3e9d-11e7-9367-b8975a6f72af","discount":20,"specification":"18.9L","company":"桶","id":"c11b0da0-3e9e-11e7-9d6e-b8975a6f72af","introduction":""http://192.168.1.77:17317/water/api/1.0/water/info/c11b0da0-3e9e-11e7-9d6e-b8975a6f72af"}
     * message : 加载成功
     * timestamp : 2017-07-21 16:29:55
     */

    private String event;
    private DataBean data;
    private String message;
    private String timestamp;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * store_id : c48f4240-3e9d-11e7-9d6e-b8975a6f72af
         * goods_name : 农夫山泉桶装水
         * images : [{"image_url":"20170522112435976007.jpg","id":"c11b0da0-3e9e-11e7-9d6e-b8975a6f72af"}]
         * present_price : 10000
         * category_id : e1875240-095d-11e7-a5d5-b8975a6f72af
         * is_reclaim : RETURN
         * goods_id : 61bdb660-3e9d-11e7-9367-b8975a6f72af
         * discount : 20
         * specification : 18.9L
         * company : 桶
         * id : c11b0da0-3e9e-11e7-9d6e-b8975a6f72af
         * introduction : http://192.168.1.77:17317/water/api/1.0/water/info/c11b0da0-3e9e-11e7-9d6e-b8975a6f72af
         */

        private String store_id;
        private String goods_name;
        private int present_price;
        private String category_id;
        private String is_reclaim;
        private String goods_id;
        private int discount;
        private String specification;
        private String company;
        private String id;
        private String introduction;
        private List<ImagesBean> images;

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getPresent_price() {
            return present_price;
        }

        public void setPresent_price(int present_price) {
            this.present_price = present_price;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getIs_reclaim() {
            return is_reclaim;
        }

        public void setIs_reclaim(String is_reclaim) {
            this.is_reclaim = is_reclaim;
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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
            /**
             * image_url : 20170522112435976007.jpg
             * id : c11b0da0-3e9e-11e7-9d6e-b8975a6f72af
             */

            private String image_url;
            private String id;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
