package com.lwj.springbootexample.pulsar;

import com.lwj.springbootexample.serialize.Serializer;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public abstract class BaseMsgHandler<T extends Msg> {

    public abstract boolean process(T msg);

    public final boolean process(byte[] bytes, Serializer serializer) {
        T obj = null;
        try {
            obj = serializer.deserialize(bytes, getMsgClass());
        } catch (IOException e) {
            return false;
        }
        return process(obj);
    }

    public final Class<T> getMsgClass(){
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
