package com.lwj.springbootexample.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaDelayConsumer {

    @KafkaListener(topics = "kafka-topic-delay",groupId = "group-1")
    public void listen(ConsumerRecord<?,?> record) {
        long timestamp = record.timestamp();
        if (System.currentTimeMillis() >= timestamp){
            log.info("record delay  = {} " ,record);
            log.info("Received delay message: {}", record.value());
        }

    }
}
