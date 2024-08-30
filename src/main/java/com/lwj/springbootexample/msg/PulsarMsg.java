package com.lwj.springbootexample.msg;

import com.alibaba.fastjson.JSONObject;
import com.lwj.springbootexample.pulsar.Msg;
import lombok.Data;

@Data
public class PulsarMsg<T> extends Msg {

    private int type;

    private T content;

    public PulsarMsg(int type, T content) {
        this.type = type;
        this.content = content;
    }

}
