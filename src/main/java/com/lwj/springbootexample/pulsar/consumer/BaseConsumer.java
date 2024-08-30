package com.lwj.springbootexample.pulsar.consumer;

import com.lwj.springbootexample.pulsar.handler.InBoundMsgHandler;
import com.lwj.springbootexample.serialize.HessianSerializer;
import com.lwj.springbootexample.serialize.Serializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
@Component
public abstract class BaseConsumer<T> {

    private final PulsarClient pulsarClient;

    private Consumer<T> consumer;

    private final Serializer serializer;

    @Resource
    InBoundMsgHandler inBoundMsgHandler;

    public BaseConsumer(PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
        serializer = new HessianSerializer();
    }

    @PostConstruct
    public void init() {
        inBoundMsgHandler.registerConsumer(getMsgType(), this);
        try {
            consumer = (Consumer<T>) pulsarClient.newConsumer()
                    .topic(getTopic())
                    .subscriptionName(getSubscribe())
                    .subscriptionType(SubscriptionType.Shared)
                    .subscribe();
            startConsumer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract boolean consume(T content);

    public void startConsumer() {
        new Thread(() ->{
            while (true){
                Message<T> msg = null;
                try {
                    // 处理消息
                    msg = consumer.receive();
                    boolean flag = inBoundMsgHandler.process(msg.getData(), serializer);
                    if (flag) {
                        consumer.acknowledge(msg);
                    }else{
                        consumer.negativeAcknowledge(msg);
                    }
                } catch (Exception e) {
                    if (msg != null){
                        log.error("receive message error: {}", msg, e);
                        consumer.negativeAcknowledge(msg);
                    }
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


    protected abstract int getMsgType();

    protected abstract String getTopic();

    protected abstract String getSubscribe();
}
