package com.lwj.springbootexample.pulsar.consumer;

import com.lwj.springbootexample.pulsar.BaseConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class StringConsumer extends BaseConsumer<String> {


    public StringConsumer(PulsarClient pulsarClient) {
        super(pulsarClient);
    }

    @Override
    public boolean consume(String content) {
        log.info("StringConsumer receive msg:{}", content);
        return true;
    }

    @Override
    protected int getMsgType() {
        return 1;
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
