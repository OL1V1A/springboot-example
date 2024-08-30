package com.lwj.springbootexample.pulsar.producer;

import com.lwj.springbootexample.serialize.HessianSerializer;
import com.lwj.springbootexample.serialize.Serializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.ProducerAccessMode;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public abstract class BaseProducer {
    protected final PulsarClient pulsarClient;

    protected Producer<byte[]> producer;

    protected final Serializer serializer;

    protected BaseProducer(PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
        this.serializer = new HessianSerializer();
        initProducer();
    }

    protected abstract void initProducer();

//    private void initProducer() {
//        try {
//            producer = pulsarClient.newProducer()
//                    .topic(getTopic())
//                    .create();
//        } catch (PulsarClientException e) {
//            log.error("init producer error :", e);
//            throw new RuntimeException(e);
//        }
//    }
    public void sendMessage(Object message){
        try {
            producer.send(serializer.serialize(message));
        } catch (Exception e) {
            log.error("send message error :", e);
            throw new RuntimeException(e);
        }
    }

    protected abstract String getTopic();

}
