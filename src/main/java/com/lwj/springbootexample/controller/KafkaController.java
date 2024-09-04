package com.lwj.springbootexample.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwj.springbootexample.base.Result;
import com.lwj.springbootexample.enumeration.Auth;
import com.lwj.springbootexample.kafka.KafkaProducer;
import com.lwj.springbootexample.model.User;
import com.lwj.springbootexample.msg.PulsarMsg;
import com.lwj.springbootexample.pulsar.producer.BaseProducer;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @GetMapping("send")
    @Auth
    public Result<String> sendMsg(@RequestParam("msg") String msg) {
        producer.send("kafka-topic-1",msg);
        return Result.success(msg);
    }

}
