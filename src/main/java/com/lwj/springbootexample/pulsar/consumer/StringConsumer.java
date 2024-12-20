package com.lwj.springbootexample.pulsar.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@ConditionalOnBean(PulsarClient.class)
public class StringConsumer extends BaseConsumer<String> {


    public StringConsumer(PulsarClient pulsarClient) {
        super(pulsarClient);
    }

    @Override
    protected void initConsumer() {
        try {
            consumer = (Consumer) pulsarClient.newConsumer()
                    .topic(getTopic())
                    .subscriptionName(getSubscribe())
                    .subscriptionType(SubscriptionType.Exclusive)
                    .subscribe();
            log.info("init {} consumer success :", getClass().getSimpleName());
        } catch (Exception e) {
            log.error("init {} consumer error : {}", getClass().getSimpleName(),e);
        }
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
        return "test-subscribe-string";
    }
}
