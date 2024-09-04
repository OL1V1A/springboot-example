package com.lwj.springbootexample.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Slf4j
@Component
public class KafkaProducer {

    @Resource
    KafkaTemplate<String,String> kafkaTemplate;

    public void send(String topic,String message){
        log.info("KafkaProducer 发送消息：{}",message);
        kafkaTemplate.send(topic,message);
    }
}
