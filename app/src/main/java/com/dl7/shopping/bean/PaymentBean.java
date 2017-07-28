package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-24.
 */

public class PaymentBean {

    /**
     * event : 200
     * data : [{"water_group":[{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","goods_name":"农夫山泉桶装水","create_time":"2017-06-16 14:43:11","image_url":"20170522112435976007.jpg","address_id":"2f2f2360-4135-11e7-a5eb-00163e062c7a","goods_id":"61bdb660-3e9d-11e7-9367-b8975a6f72af","brand_name":"农夫山泉","brand_id":"49d25c40-3e9d-11e7-9367-b8975a6f72af","number":26,"update_time":"2017-07-13 17:51:53","total_num":16,"is_del":"0","id":"1108a740-525f-11e7-b4f7-00163e062c7a"},{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","goods_name":"农夫山泉桶装水","create_time":"2017-07-22 09:36:51","image_url":"20170522112435976007.jpg","address_id":"8c716090-445c-11e7-8ace-b0d59d46982f","goods_id":"61bdb660-3e9d-11e7-9367-b8975a6f72af","brand_name":"农夫山泉","brand_id":"49d25c40-3e9d-11e7-9367-b8975a6f72af","number":20,"total_num":50,"is_del":"0","id":"3c59c420-6e7e-11e7-9f46-00e066f08ac3"}],"brand_name":"农夫山泉"},{"water_group":[{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","goods_name":"怡宝桶装水","create_time":"2017-07-22 09:35:00","image_url":"20170522112450443008.jpg","address_id":"8c716090-445c-11e7-8ace-b0d59d46982f","goods_id":"6ea58ba0-3e9d-11e7-9367-b8975a6f72af","brand_name":"怡宝","brand_id":"528624c0-3e9d-11e7-9367-b8975a6f72af","number":100,"update_time":"2017-07-22 09:35:12","total_num":0,"is_del":"0","id":"fa0db860-6e7d-11e7-8b3c-0036766903e9"}],"brand_name":"怡宝"}]
     * message : 加载成功
     * timestamp : 2017-07-24 17:38:40
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
         * water_group : [{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","goods_name":"农夫山泉桶装水","create_time":"2017-06-16 14:43:11","image_url":"20170522112435976007.jpg","address_id":"2f2f2360-4135-11e7-a5eb-00163e062c7a","goods_id":"61bdb660-3e9d-11e7-9367-b8975a6f72af","brand_name":"农夫山泉","brand_id":"49d25c40-3e9d-11e7-9367-b8975a6f72af","number":26,"update_time":"2017-07-13 17:51:53","total_num":16,"is_del":"0","id":"1108a740-525f-11e7-b4f7-00163e062c7a"},{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","member_id":"6d7e1950-4063-11e7-ad78-00163e062c7a","goods_name":"农夫山泉桶装水","create_time":"2017-07-22 09:36:51","image_url":"20170522112435976007.jpg","address_id":"8c716090-445c-11e7-8ace-b0d59d46982f","goods_id":"61bdb660-3e9d-11e7-9367-b8975a6f72af","brand_name":"农夫山泉","brand_id":"49d25c40-3e9d-11e7-9367-b8975a6f72af","number":20,"total_num":50,"is_del":"0","id":"3c59c420-6e7e-11e7-9f46-00e066f08ac3"}]
         * brand_name : 农夫山泉
         */
        private boolean is_all_check;
        private String brand_name;
        private List<WaterGroupBean> water_group;

        public boolean isIs_all_check() {
            return is_all_check;
        }

        public void setIs_all_check(boolean is_all_check) {
            this.is_all_check = is_all_check;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public List<WaterGroupBean> getWater_group() {
            return water_group;
        }

        public void setWater_group(List<WaterGroupBean> water_group) {
            this.water_group = water_group;
        }

        public static class WaterGroupBean {
            /**
             * store_id : c48f4240-3e9d-11e7-9d6e-b8975a6f72af
             * member_id : 6d7e1950-4063-11e7-ad78-00163e062c7a
             * goods_name : 农夫山泉桶装水
             * create_time : 2017-06-16 14:43:11
             * image_url : 20170522112435976007.jpg
             * address_id : 2f2f2360-4135-11e7-a5eb-00163e062c7a
             * goods_id : 61bdb660-3e9d-11e7-9367-b8975a6f72af
             * brand_name : 农夫山泉
             * brand_id : 49d25c40-3e9d-11e7-9367-b8975a6f72af
             * number : 26
             * update_time : 2017-07-13 17:51:53
             * total_num : 16
             * is_del : 0
             * id : 1108a740-525f-11e7-b4f7-00163e062c7a
             */

            private String store_id;
            private String member_id;
            private String goods_name;
            private String create_time;
            private String image_url;
            private String address_id;
            private String goods_id;
            private String brand_name;
            private String brand_id;
            private int number;
            private String update_time;
            private int total_num;
            private String is_del;
            private String id;
            private boolean is_check;
            private int num;

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getAddress_id() {
                return address_id;
            }

            public void setAddress_id(String address_id) {
                this.address_id = address_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public int getTotal_num() {
                return total_num;
            }

            public void setTotal_num(int total_num) {
                this.total_num = total_num;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIs_check() {
                return is_check;
            }

            public void setIs_check(boolean is_check) {
                this.is_check = is_check;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }
    }
}
