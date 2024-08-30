package com.lwj.springbootexample.serialize;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

public class JsonSerializer implements Serializer{
    @Override
    public byte[] serialize(Object obj) throws IOException {
        return obj == null ? null : JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
        return bytes == null ? null : JSON.parseObject(bytes, clazz);
    }
}
