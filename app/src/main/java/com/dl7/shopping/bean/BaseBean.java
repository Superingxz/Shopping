package com.dl7.shopping.bean;

/**
 * Created by Administrator on 2017/8/4.
 */

public class BaseBean<T> {
    private String event;
    private String message;
    private String timestamp;
    private T data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
