package com.lwj.springbootexample.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "kafka-topic-1",groupId = "group-1")
    public void listen(ConsumerRecord<?,?> consumerRecord) {
        log.info("record = {} " ,consumerRecord);
        log.info("Received message: {}", consumerRecord.value());
    }
}
