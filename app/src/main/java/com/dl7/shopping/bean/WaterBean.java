package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-20.
 */

public class WaterBean {

    /**
     * event : 200
     * data : {"WATER_A":[{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","goods_name":"农夫山泉桶装水","category_name":"桶装水","present_price":10000,"category_id":"e1875240-095d-11e7-a5d5-b8975a6f72af","image_url":"20170522111851084002.jpg","specification":"18.9L","discount":20,"company":"桶","id":"c11b0da0-3e9e-11e7-9d6e-b8975a6f72af"},{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","goods_name":"怡宝桶装水","category_name":"桶装水","present_price":10000,"category_id":"e1875240-095d-11e7-a5d5-b8975a6f72af","image_url":"20170522111913272003.jpg","specification":"18.9L","discount":30,"company":"桶","id":"df0bb210-3e9e-11e7-9d6e-b8975a6f72af"},{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","goods_name":"水之星桶装水","category_name":"桶装水","present_price":10000,"category_id":"e1875240-095d-11e7-a5d5-b8975a6f72af","image_url":"20170525144731050001.jpg","specification":"18.9L","discount":30,"company":"桶","id":"71eb6e30-4116-11e7-8108-00163e062c7a"},{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","goods_name":"益寿泉桶装水","category_name":"桶装水","present_price":10000,"category_id":"e1875240-095d-11e7-a5d5-b8975a6f72af","image_url":"20170525140046608001.jpg","specification":"18.9L","discount":20,"company":"桶","id":"63251c50-4113-11e7-8108-00163e062c7a"}],"WATER_C":[{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","goods_name":"益寿泉 空桶","category_name":"空桶","present_price":4000,"category_id":"2f8fb1f0-0966-11e7-bd2c-b8975a6f72af","image_url":"20170614153103856000.jpg","specification":"18.9L","discount":0,"company":"桶","id":"02d71b70-50d4-11e7-917b-b8975a6f72af"},{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","goods_name":" 农夫山泉  空桶","category_name":"空桶","present_price":10000,"category_id":"2f8fb1f0-0966-11e7-bd2c-b8975a6f72af","image_url":"20170605183726498000.jpg","specification":"18.9L","discount":0,"company":"桶","id":"65f7e830-3e9e-11e7-9d6e-b8975a6f72af"},{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","goods_name":"怡宝  空桶","category_name":"空桶","present_price":4500,"category_id":"2f8fb1f0-0966-11e7-bd2c-b8975a6f72af","image_url":"20170605183953516002.jpg","specification":"18.9L","discount":0,"company":"桶","id":"aba98ad0-49db-11e7-addf-b8975a6f72af"}],"WATER_B":[{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","goods_name":"农夫山泉支装水","category_name":"支装水","present_price":10000,"category_id":"a839d810-08b7-11e7-a4c1-b8975a6f72af","image_url":"20170522112048538004.jpg","specification":"500ml","discount":20,"company":"24 瓶/箱","id":"32c14aa0-3e9f-11e7-9d6e-b8975a6f72af"}],"WATER_D":[{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","goods_name":"可加热恒温饮水机","category_name":"饮水机","present_price":10000,"category_id":"6483aa80-49dd-11e7-93b5-b8975a6f72af","image_url":"20170605185947994005.jpg","specification":"140cm * 30cm","discount":20,"company":"台","id":"73a63810-49de-11e7-addf-b8975a6f72af"}]}
     * message : 加载成功
     * timestamp : 2017-07-20 11:35:26
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
        private List<WATERABean> WATER_A;
        private List<WATERCBean> WATER_C;
        private List<WATERBBean> WATER_B;
        private List<WATERDBean> WATER_D;

        public List<WATERABean> getWATER_A() {
            return WATER_A;
        }

        public void setWATER_A(List<WATERABean> WATER_A) {
            this.WATER_A = WATER_A;
        }

        public List<WATERCBean> getWATER_C() {
            return WATER_C;
        }

        public void setWATER_C(List<WATERCBean> WATER_C) {
            this.WATER_C = WATER_C;
        }

        public List<WATERBBean> getWATER_B() {
            return WATER_B;
        }

        public void setWATER_B(List<WATERBBean> WATER_B) {
            this.WATER_B = WATER_B;
        }

        public List<WATERDBean> getWATER_D() {
            return WATER_D;
        }

        public void setWATER_D(List<WATERDBean> WATER_D) {
            this.WATER_D = WATER_D;
        }

        public static class WATERABean {
            /**
             * store_id : c48f4240-3e9d-11e7-9d6e-b8975a6f72af
             * goods_name : 农夫山泉桶装水
             * category_name : 桶装水
             * present_price : 10000
             * category_id : e1875240-095d-11e7-a5d5-b8975a6f72af
             * image_url : 20170522111851084002.jpg
             * specification : 18.9L
             * discount : 20
             * company : 桶
             * id : c11b0da0-3e9e-11e7-9d6e-b8975a6f72af
             */

            private String store_id;
            private String goods_name;
            private String category_name;
            private int present_price;
            private String category_id;
            private String image_url;
            private String specification;
            private int discount;
            private String company;
            private String id;

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

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
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

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
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
        }

        public static class WATERCBean {
            /**
             * store_id : c48f4240-3e9d-11e7-9d6e-b8975a6f72af
             * goods_name : 益寿泉 空桶
             * category_name : 空桶
             * present_price : 4000
             * category_id : 2f8fb1f0-0966-11e7-bd2c-b8975a6f72af
             * image_url : 20170614153103856000.jpg
             * specification : 18.9L
             * discount : 0
             * company : 桶
             * id : 02d71b70-50d4-11e7-917b-b8975a6f72af
             */

            private String store_id;
            private String goods_name;
            private String category_name;
            private int present_price;
            private String category_id;
            private String image_url;
            private String specification;
            private int discount;
            private String company;
            private String id;

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

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
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

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
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
        }

        public static class WATERBBean {
            /**
             * store_id : c48f4240-3e9d-11e7-9d6e-b8975a6f72af
             * goods_name : 农夫山泉支装水
             * category_name : 支装水
             * present_price : 10000
             * category_id : a839d810-08b7-11e7-a4c1-b8975a6f72af
             * image_url : 20170522112048538004.jpg
             * specification : 500ml
             * discount : 20
             * company : 24 瓶/箱
             * id : 32c14aa0-3e9f-11e7-9d6e-b8975a6f72af
             */

            private String store_id;
            private String goods_name;
            private String category_name;
            private int present_price;
            private String category_id;
            private String image_url;
            private String specification;
            private int discount;
            private String company;
            private String id;

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

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
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

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
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
        }

        public static class WATERDBean {
            /**
             * store_id : c48f4240-3e9d-11e7-9d6e-b8975a6f72af
             * goods_name : 可加热恒温饮水机
             * category_name : 饮水机
             * present_price : 10000
             * category_id : 6483aa80-49dd-11e7-93b5-b8975a6f72af
             * image_url : 20170605185947994005.jpg
             * specification : 140cm * 30cm
             * discount : 20
             * company : 台
             * id : 73a63810-49de-11e7-addf-b8975a6f72af
             */

            private String store_id;
            private String goods_name;
            private String category_name;
            private int present_price;
            private String category_id;
            private String image_url;
            private String specification;
            private int discount;
            private String company;
            private String id;

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

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
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

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
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
        }
    }
}
