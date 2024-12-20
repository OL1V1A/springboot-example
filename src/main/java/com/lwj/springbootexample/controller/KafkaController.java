package com.lwj.springbootexample.controller;

import com.lwj.springbootexample.base.Result;
import com.lwj.springbootexample.enumeration.Auth;
import com.lwj.springbootexample.kafka.KafkaDelayProducer;
import com.lwj.springbootexample.kafka.KafkaProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("kafka")
@Auth
public class KafkaController {

    @Resource
    KafkaProducer producer;

    @Resource
    KafkaDelayProducer kafkaDelayProducer;

    @GetMapping("send")
    @Auth
    public Result<String> sendMsg(@RequestParam("msg") String msg) {
        producer.send("kafka-topic-1",msg);
        return Result.success(msg);
    }

    @GetMapping("sendDelay")
    @Auth
    public Result<String> sendDelay(@RequestParam("msg") String msg) {
        kafkaDelayProducer.send("kafka-topic-delay",msg,5000);
        return Result.success(msg);
    }

}
