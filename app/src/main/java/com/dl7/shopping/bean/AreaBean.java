package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-17.
 */

public class AreaBean {
    /**
     * event : 200
     * data : [{"city_name":"梅州市","code":"011201","create_time":"2017-03-20 13:59:23","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"梅江区","is_del":"0","id":"5de9d1a0-0d32-11e7-a212-b8975a6f72af","city_id":"86244d10-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"city_name":"梅州市","code":"011202","create_time":"2017-03-20 13:59:52","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"梅县区","is_del":"0","id":"6f56b9d0-0d32-11e7-a212-b8975a6f72af","city_id":"86244d10-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"city_name":"梅州市","code":"011203","create_time":"2017-03-20 14:00:49","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"大埔县","is_del":"0","id":"7f8d1dd0-0d32-11e7-a212-b8975a6f72af","city_id":"86244d10-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"city_name":"梅州市","code":"011204","create_time":"2017-03-20 14:00:43","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"丰顺县","is_del":"0","id":"8df02160-0d32-11e7-a212-b8975a6f72af","city_id":"86244d10-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"city_name":"梅州市","code":"011205","create_time":"2017-03-20 14:01:23","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"五华县","is_del":"0","id":"a5a688d0-0d32-11e7-a212-b8975a6f72af","city_id":"86244d10-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"city_name":"梅州市","code":"011206","create_time":"2017-03-20 14:01:49","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"平远县","is_del":"0","id":"b5249950-0d32-11e7-a212-b8975a6f72af","city_id":"86244d10-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"city_name":"梅州市","code":"011207","create_time":"2017-03-20 14:02:15","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"蕉岭县","is_del":"0","id":"c4d66400-0d32-11e7-a212-b8975a6f72af","city_id":"86244d10-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"city_name":"梅州市","code":"011208","create_time":"2017-03-20 14:02:38","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"兴宁市","is_del":"0","id":"d26045a0-0d32-11e7-a212-b8975a6f72af","city_id":"86244d10-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"},{"city_name":"梅州市","code":"011209","create_time":"2017-03-20 14:02:56","province_id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af","name":"其他区","is_del":"0","id":"dcf0bd60-0d32-11e7-a212-b8975a6f72af","city_id":"86244d10-0d20-11e7-aa69-b8975a6f72af","province_name":"广东省"}]
     * message : 加载成功
     * timestamp : 2017-07-17 18:38:09
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
         * city_name : 梅州市
         * code : 011201
         * create_time : 2017-03-20 13:59:23
         * province_id : ac097e20-0d1f-11e7-aa69-b8975a6f72af
         * name : 梅江区
         * is_del : 0
         * id : 5de9d1a0-0d32-11e7-a212-b8975a6f72af
         * city_id : 86244d10-0d20-11e7-aa69-b8975a6f72af
         * province_name : 广东省
         */

        private String city_name;
        private String code;
        private String create_time;
        private String province_id;
        private String name;
        private String is_del;
        private String id;
        private String city_id;
        private String province_name;

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

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

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }
    }
}
