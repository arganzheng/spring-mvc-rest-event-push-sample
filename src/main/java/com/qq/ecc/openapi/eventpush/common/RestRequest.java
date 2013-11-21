package com.qq.ecc.openapi.eventpush.common;

import com.qq.ecc.openapi.eventpush.constant.Constants;

/**
 * REST 请求格式，这样可以利用@RequestBody注解。
 * 
 * @author arganzheng
 * @date 2013-11-21
 */
public class RestRequest<T> {

    private String topic = Constants.EMPTY_STRING;
    private String event = Constants.EMPTY_STRING;
    private long   uin;
    private T      data;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestRequest [topic=" + topic + ", event=" + event + ", uin=" + uin + ", data=" + data + "]";
    }

}
