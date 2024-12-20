package com.lwj.springbootexample.pulsar.producer;

import com.lwj.springbootexample.serialize.HessianSerializer;
import com.lwj.springbootexample.serialize.Serializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public  abstract class BaseProducer<PulsarMsg> {
    protected final PulsarClient pulsarClient;

    protected Producer<byte[]> producer;

    protected final Serializer serializer;

    protected BaseProducer(PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
        this.serializer = new HessianSerializer();
        initProducer();
    }

    protected void initProducer() {
        try {
            producer = pulsarClient.newProducer()
                    .topic(getTopic())
                    .producerName(getClass().getSimpleName())
                    .create();
        } catch (PulsarClientException e) {
            log.error("init producer error :", e);
            throw new RuntimeException(e);
        }
    }

    public abstract void sendMessage(PulsarMsg message);


    protected abstract String getTopic();

}
