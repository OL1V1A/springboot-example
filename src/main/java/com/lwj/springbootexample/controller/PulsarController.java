package com.lwj.springbootexample.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwj.springbootexample.base.Result;
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
@RequestMapping("pulsar")
public class PulsarController {

    @Resource
    @Qualifier("topicProducer")
    BaseProducer producer;

    @GetMapping("sendMsg")
    public Result<String> sendMsg(@RequestParam("msg") String msg) {
        PulsarMsg<String> msgObj = new PulsarMsg<>(1,msg);
        producer.sendMessage(msgObj);
        return Result.success();
    }

    @GetMapping("sendMsgObj")
    public Result<String> sendMsgObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name","lwj");
        jsonObj.put("age",18);
        PulsarMsg<JSONObject> msgObj = new PulsarMsg<>(2,jsonObj);
        producer.sendMessage(msgObj);
        return Result.success();
    }

    @GetMapping("sendMsgUser")
    public Result<String> sendMsgUser() {
        User user = new User("王天霸",99,"男","1912-01-09");
        PulsarMsg<User> msgObj = new PulsarMsg<>(3,user);
        producer.sendMessage(msgObj);
        return Result.success();
    }
}
