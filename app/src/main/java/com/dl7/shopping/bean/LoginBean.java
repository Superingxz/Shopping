package com.dl7.shopping.bean;

/**
 * Created by MC.Zeng on 2017-07-13.
 */

public class LoginBean {

    /**
     * event : 200
     * data : {"member_id":"144111","image":"","name":"","mobile":"5555555","account":"222"}
     * message : 登录成功
     * timestamp : 2017-07-13 17:48:39
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
         * member_id : 222
         * image :
         * name :
         * mobile : 22
         * account :22
         */

        private String member_id;
        private String image;
        private String name;
        private String mobile;
        private String account;

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }
}
