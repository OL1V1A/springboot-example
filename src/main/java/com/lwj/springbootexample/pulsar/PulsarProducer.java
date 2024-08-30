package com.lwj.springbootexample.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Component;
@Slf4j
//@Component
public class PulsarProducer {

    private final PulsarClient pulsarClient;

    private Producer<byte[]> producer;

    public PulsarProducer(PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
        initProducer();
    }

    private void initProducer() {
        try {
            producer = pulsarClient.newProducer()
                    .topic("test-topic-1")
                    .create();
        } catch (PulsarClientException e) {
            log.error("init producer error :", e);
            throw new RuntimeException(e);
        }
    }
    public void sendMessage(String message){
        try {
            producer.send(message.getBytes());
        } catch (PulsarClientException e) {
            log.error("send message error :", e);
            throw new RuntimeException(e);
        }
    }
}
