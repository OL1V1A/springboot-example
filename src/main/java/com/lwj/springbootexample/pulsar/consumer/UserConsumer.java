package com.lwj.springbootexample.pulsar.consumer;

import com.lwj.springbootexample.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserConsumer extends BaseConsumer<User> {


    public UserConsumer(PulsarClient pulsarClient) {
        super(pulsarClient);
    }

    @Override
    public boolean consume(User content) {
        log.info("UserConsumer receive msg:{}", content);
        return true;
    }

    @Override
    protected int getMsgType() {
        return 3;
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
