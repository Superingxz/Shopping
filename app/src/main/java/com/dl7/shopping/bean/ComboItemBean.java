package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-29.
 */

public class ComboItemBean {

    /**
     * event : 200
     * data : [{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","create_user_id":"75407360-4057-11e7-a4e8-00163e062c7a","goods_name":"怡宝桶装水","notes":"每月仅限2桶","company_id":"a4d78bc0-2f2e-11e7-88c6-b8975a6f72af","create_time":"2017-07-08 11:29:24","goods_id":"6ea58ba0-3e9d-11e7-9367-b8975a6f72af","discount":20,"brand_name":"怡宝","sort":1,"brand_id":"528624c0-3e9d-11e7-9367-b8975a6f72af","number":20,"money":21000,"name":"怡宝20桶","is_del":"0","goods_company":"桶","available_num":2,"store_name":"广大一号水店","id":"9df402a0-525d-11e7-b65a-00163e062c7a"},{"store_id":"c48f4240-3e9d-11e7-9d6e-b8975a6f72af","create_user_id":"75407360-4057-11e7-a4e8-00163e062c7a","goods_name":"怡宝桶装水","notes":"5倍爱心，每月可用8桶","company_id":"a4d78bc0-2f2e-11e7-88c6-b8975a6f72af","create_time":"2017-07-08 11:29:02","goods_id":"6ea58ba0-3e9d-11e7-9367-b8975a6f72af","discount":50,"brand_name":"怡宝","sort":2,"brand_id":"528624c0-3e9d-11e7-9367-b8975a6f72af","number":80,"money":100000,"name":"怡宝80桶","is_del":"0","goods_company":"桶","available_num":8,"store_name":"广大一号水店","id":"967aac90-638d-11e7-a207-b8975a6f72af"}]
     * message : 加载成功
     * timestamp : 2017-07-29 16:51:24
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
         * store_id : c48f4240-3e9d-11e7-9d6e-b8975a6f72af
         * create_user_id : 75407360-4057-11e7-a4e8-00163e062c7a
         * goods_name : 怡宝桶装水
         * notes : 每月仅限2桶
         * company_id : a4d78bc0-2f2e-11e7-88c6-b8975a6f72af
         * create_time : 2017-07-08 11:29:24
         * goods_id : 6ea58ba0-3e9d-11e7-9367-b8975a6f72af
         * discount : 20
         * brand_name : 怡宝
         * sort : 1
         * brand_id : 528624c0-3e9d-11e7-9367-b8975a6f72af
         * number : 20
         * money : 21000
         * name : 怡宝20桶
         * is_del : 0
         * goods_company : 桶
         * available_num : 2
         * store_name : 广大一号水店
         * id : 9df402a0-525d-11e7-b65a-00163e062c7a
         */

        private String store_id;
        private String create_user_id;
        private String goods_name;
        private String notes;
        private String company_id;
        private String create_time;
        private String goods_id;
        private int discount;
        private String brand_name;
        private int sort;
        private String brand_id;
        private int number;
        private int money;
        private String name;
        private String is_del;
        private String goods_company;
        private int available_num;
        private String store_name;
        private String id;
        private String score;
        private String addressID;

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getCreate_user_id() {
            return create_user_id;
        }

        public void setCreate_user_id(String create_user_id) {
            this.create_user_id = create_user_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
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

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
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

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getGoods_company() {
            return goods_company;
        }

        public void setGoods_company(String goods_company) {
            this.goods_company = goods_company;
        }

        public int getAvailable_num() {
            return available_num;
        }

        public void setAvailable_num(int available_num) {
            this.available_num = available_num;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getScore() {
            return score;
        }

        public String getAddressID() {
            return addressID;
        }

        public void setAddressID(String addressID) {
            this.addressID = addressID;
        }
    }
}
