package com.lwj.springbootexample.pulsar;

import com.lwj.springbootexample.serialize.HessianSerializer;
import com.lwj.springbootexample.serialize.Serializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BaseProducer {
    private final PulsarClient pulsarClient;

    private Producer<byte[]> producer;

    private final Serializer serializer;

    public BaseProducer(PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
        this.serializer = new HessianSerializer();
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
    public void sendMessage(Object message){
        try {
            producer.send(serializer.serialize(message));
        } catch (Exception e) {
            log.error("send message error :", e);
            throw new RuntimeException(e);
        }
    }

}
