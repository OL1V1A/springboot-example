package com.lwj.springbootexample.msg;

import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
public class PulsarMsg<T> extends Msg {

    private int type;

    private T content;

    /**
     * 延迟消息类型
     * false 往后延迟时长 delayTime
     * true 具体时间点
     */
    private boolean delayType;

    private long delayTime;

    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public PulsarMsg(int type, T content) {
        this.type = type;
        this.content = content;
    }

    public PulsarMsg(int type, T content, long delayTime) {
        this.type = type;
        this.content = content;
        this.delayTime = delayTime;
    }

    public PulsarMsg(int type, T content, long delayTime,TimeUnit timeUnit) {
        this.type = type;
        this.content = content;
        this.delayTime = delayTime;
        this.timeUnit = timeUnit;
    }

    public PulsarMsg(int type, T content, long delayTime, boolean delayType) {
        this.type = type;
        this.content = content;
        this.delayTime = delayTime;
        this.delayType = delayType;
    }

    public PulsarMsg(int type, T content, long delayTime,TimeUnit timeUnit, boolean delayType) {
        this.type = type;
        this.content = content;
        this.delayTime = delayTime;
        this.timeUnit = timeUnit;
        this.delayType = delayType;
    }

}
