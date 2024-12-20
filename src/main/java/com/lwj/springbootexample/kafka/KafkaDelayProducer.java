package com.lwj.springbootexample.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class KafkaDelayProducer {

    @Resource
    KafkaTemplate<String,String> kafkaTemplate;

    public void send(String topic,String message,long delayTime){
        long time = System.currentTimeMillis() + delayTime;
        log.info("当前时间：{}",System.currentTimeMillis());
        log.info("消息时间：{}",time);
        log.info("KafkaProducer 发送消息：{}",message);
        ProducerRecord<String,String> record = new ProducerRecord<>(topic,null,time,null,message);
        kafkaTemplate.send(record);
    }
}
