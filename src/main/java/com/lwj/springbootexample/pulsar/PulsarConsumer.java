package com.lwj.springbootexample.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
//@Component
public class PulsarConsumer {

    private final PulsarClient pulsarClient;

    private Consumer<byte[]> consumer;

    public PulsarConsumer(PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
    }
    @PostConstruct
    public void init() throws Exception {
        consumer = pulsarClient.newConsumer()
                .topic("test-topic")
                .subscriptionName("test-subscription")
                .subscribe();
        startConsumer();
    }

    private void startConsumer(){
        new Thread(() ->{
            while (true){
                Message<byte[]> msg = null;
                try {
                    // 处理消息
                    msg = consumer.receive();
                    String message = new String(msg.getData());
                    log.info("receive message: {}", message);
                    consumer.acknowledge(msg);
                } catch (Exception e) {
                    if (msg != null){
                        consumer.negativeAcknowledge(msg);
                    }
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
