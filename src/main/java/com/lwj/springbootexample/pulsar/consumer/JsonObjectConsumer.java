package com.lwj.springbootexample.pulsar.consumer;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonObjectConsumer extends BaseConsumer<JSONObject> {


    public JsonObjectConsumer(PulsarClient pulsarClient) {
        super(pulsarClient);
    }

    @Override
    public boolean consume(JSONObject content) {
        log.info("JsonObjectConsumer receive msg:{}", content);
        return true;
    }

    @Override
    protected int getMsgType() {
        return 2;
    }

    @Override
    protected String getTopic() {
        return "test-topic-1";
    }

    @Override
    protected String getSubscribe() {
        return "test-subscribe";
    }
}
