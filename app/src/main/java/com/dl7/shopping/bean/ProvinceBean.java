package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-17.
 */

public class ProvinceBean {
    /**
     * event : 200
     * data : [{"code":"01","create_time":"2017-03-20 11:45:33","name":"广东省","is_del":"0","id":"ac097e20-0d1f-11e7-aa69-b8975a6f72af"}]
     * message : 加载成功
     * timestamp : 2017-07-17 17:04:37
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
         * code : 01
         * create_time : 2017-03-20 11:45:33
         * name : 广东省
         * is_del : 0
         * id : ac097e20-0d1f-11e7-aa69-b8975a6f72af
         */

        private String code;
        private String create_time;
        private String name;
        private String is_del;
        private String id;

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
    }
}
