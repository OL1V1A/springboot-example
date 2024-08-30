package com.lwj.springbootexample.pulsar.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopicProducer extends BaseProducer{

    public TopicProducer(PulsarClient pulsarClient) {
        super(pulsarClient);
    }

    @Override
    protected void initProducer() {
            try {
                producer = pulsarClient.newProducer()
                        .topic(getTopic())
                        .create();
            } catch (PulsarClientException e) {
                log.error("init producer error :", e);
                throw new RuntimeException(e);
            }
    }

    @Override
    protected String getTopic() {
        return "test-topic-1";
    }
}
