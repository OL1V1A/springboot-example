package com.lwj.springbootexample.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class KryoSerializer{

    /**
     * 序列化
     *
     * @param obj 序列化对象
     * @return
     */
    public byte[] serialize(Object obj) {
        if (obj == null) return null;
        Kryo kryo = KRYO_THREAD_LOCAL.get();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        kryo.writeClassAndObject(output, obj);
        output.close();
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 反序列化
     *
     * @param bytes 二进制字节码
     * @param <T>   反序列化泛型
     * @return
     */
    public <T> T deserialize(byte[] bytes) {
        if (bytes == null) return null;
        Kryo kryo = KRYO_THREAD_LOCAL.get();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        input.close();
        return (T) kryo.readClassAndObject(input);
    }


    /**
     * Kryo线程不安全，需要ThreadLocal去维护
     * 减少每次实例化Kryo开销同时保证线程安全
     */
    private static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            kryo.setReferences(true);
            kryo.setRegistrationRequired(true);
            return kryo;
        }

    };
}
