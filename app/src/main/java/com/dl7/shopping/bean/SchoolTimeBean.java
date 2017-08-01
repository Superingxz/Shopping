package com.dl7.shopping.bean;

import java.util.List;

/**
 * Created by MC.Zeng on 2017-07-27.
 */

public class SchoolTimeBean {


    /**
     * event : 200
     * data : [{"timeData":[{"srot":0,"hour":"上午"},{"srot":1,"hour":"中午"},{"srot":2,"hour":"下午"}],"todayDay":"2017-08-01"},{"timeData":[{"srot":0,"hour":"上午"},{"srot":1,"hour":"中午"},{"srot":2,"hour":"下午"}]}]
     * message : 加载成功
     * timestamp : 2017-07-31 10:13:39
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
         * timeData : [{"srot":0,"hour":"上午"},{"srot":1,"hour":"中午"},{"srot":2,"hour":"下午"}]
         * todayDay : 2017-08-01
         */

        private String todayDay;
        private List<TimeDataBean> timeData;

        public String getTodayDay() {
            return todayDay;
        }

        public void setTodayDay(String todayDay) {
            this.todayDay = todayDay;
        }

        public List<TimeDataBean> getTimeData() {
            return timeData;
        }

        public void setTimeData(List<TimeDataBean> timeData) {
            this.timeData = timeData;
        }

        public static class TimeDataBean {
            /**
             * srot : 0
             * hour : 上午
             */

            private int srot;
            private String hour;

            public int getSrot() {
                return srot;
            }

            public void setSrot(int srot) {
                this.srot = srot;
            }

            public String getHour() {
                return hour;
            }

            public void setHour(String hour) {
                this.hour = hour;
            }
        }
    }
}
