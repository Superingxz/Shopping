package com.dl7.shopping.bean;

/**
 * Created by MC.Zeng on 2017-07-10.
 */

import java.util.List;

public class CityBean {

    /**
     * event : 200
     * data : [{"code":"0101","create_time":"2017-03-20 11:47:01","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"广州市","is_del":"0","id":"e01f3ba0-0d1f-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0102","create_time":"2017-03-20 11:47:35","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"韶关市","is_del":"0","id":"f4903c10-0d1f-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0103","create_time":"2017-03-20 11:47:48","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"深圳市","is_del":"0","id":"fc4e32e0-0d1f-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0104","create_time":"2017-03-20 11:48:02","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"珠海市","is_del":"0","id":"04c2a870-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0105","create_time":"2017-03-20 11:49:10","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"汕头市","is_del":"0","id":"220580b0-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0106","create_time":"2017-03-20 11:49:37","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"佛山市","is_del":"0","id":"3d642fa0-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0107","create_time":"2017-03-20 11:49:50","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"江门市","is_del":"0","id":"452d4a00-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0108","create_time":"2017-03-20 11:50:07","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"湛江市","is_del":"0","id":"4f52f200-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0109","create_time":"2017-03-20 11:50:23","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"茂名市","is_del":"0","id":"587819f0-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0110","create_time":"2017-03-20 11:50:41","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"肇庆市","is_del":"0","id":"633e6ec0-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0111","create_time":"2017-03-20 11:51:02","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"惠州市","is_del":"0","id":"6fc203f0-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0112","create_time":"2017-03-20 11:51:39","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"梅州市","is_del":"0","id":"86244d10-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0113","create_time":"2017-03-20 11:51:54","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"汕尾市","is_del":"0","id":"8eb2b340-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0114","create_time":"2017-03-20 11:52:10","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"河源市","is_del":"0","id":"98545e30-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0115","create_time":"2017-03-20 11:52:26","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"阳江市","is_del":"0","id":"a22cd090-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0116","create_time":"2017-03-20 11:52:52","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"清远市","is_del":"0","id":"b13bcb90-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0117","create_time":"2017-03-20 11:53:11","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"东莞市","is_del":"0","id":"bcc7ba50-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0118","create_time":"2017-03-20 11:53:52","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"中山市","is_del":"0","id":"c49dcd00-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"code":"0119","create_time":"2017-03-20 11:53:44","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"东沙群岛","is_del":"0","id":"d04c10d0-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"}]
     */

    private String event;
    private List<DataBean> data;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * code : 0101
         * create_time : 2017-03-20 11:47:01
         * province_id : ac097e20-0d1f-11e7-aa69-b8975a6f72af
         * name : 广州市
         * is_del : 0
         * id : e01f3ba0-0d1f-11e7-aa69-b8975a6f72af
         * province_name : 广东省
         */

        private String code;
        private String create_time;
        private String province_id;
        private String name;
        private String is_del;
        private String id;
        private String province_name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }
    }
}
