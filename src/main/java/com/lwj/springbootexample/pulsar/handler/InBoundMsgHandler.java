package com.lwj.springbootexample.pulsar.handler;

import com.lwj.springbootexample.msg.PulsarMsg;
import com.lwj.springbootexample.pulsar.consumer.BaseConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class  InBoundMsgHandler extends BaseMsgHandler<PulsarMsg> {


    /**
     * 消费者注册
     */
    private Map<Integer, BaseConsumer> consumers = new HashMap<>();

    @Override
    public boolean process(PulsarMsg msgObj) {
        BaseConsumer consumer = consumers.get(msgObj.getType());
        if (consumer == null) {
            log.warn("收到消息无消费者: ID=[{}],type=[{}],内容=[{}]", msgObj.getMsgId(), msgObj.getType(), msgObj.getContent());
            return false;
        }
        return consumer.consume(msgObj.getContent());
    }

    public void registerConsumer(int msgType, BaseConsumer consumer) {
        consumers.put(msgType, consumer);
    }
}
