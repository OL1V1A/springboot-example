package com.lwj.springbootexample.pulsar.producer;

import com.lwj.springbootexample.msg.PulsarMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.TypedMessageBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@ConditionalOnBean(PulsarClient.class)
public class DelayProducer<T> extends BaseProducer<PulsarMsg<T>>{

    public DelayProducer(PulsarClient pulsarClient) {
        super(pulsarClient);
    }

    public void sendMessage(PulsarMsg<T> message){
        try {
            TypedMessageBuilder<byte[]> typedMessageBuilder;
            if (message.isDelayType()){
                typedMessageBuilder = producer.newMessage().value(serializer.serialize(message))
                        .deliverAt(message.getDelayTime());

            }else{
                typedMessageBuilder = producer.newMessage().value(serializer.serialize(message))
                        .deliverAfter(message.getDelayTime(), message.getTimeUnit());
            }
            typedMessageBuilder.send();
            log.info("send message success :{}",message);
        } catch (Exception e) {
            log.error("send message error :", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String getTopic() {
        return "test-topic-1";
    }
}
