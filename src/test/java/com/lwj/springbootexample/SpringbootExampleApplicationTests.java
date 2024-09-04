package com.lwj.springbootexample;

import cn.mybatis.mp.core.mybatis.mapper.context.Pager;
import cn.mybatis.mp.core.sql.executor.chain.QueryChain;
import com.lwj.springbootexample.kafka.KafkaProducer;
import com.lwj.springbootexample.mapper.StuMapper;
import com.lwj.springbootexample.model.Stu;
import db.sql.api.cmd.LikeMode;
import org.apache.ibatis.cursor.Cursor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootExampleApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private StuMapper stuMapper;

    @Resource
    private KafkaProducer kafkaProducer;

    @Test
    @Transactional
    public void testStu() {
//
//        Stu stu = stuMapper.get(where -> {
//            where.eq(Stu::getId, 1);
//        });
//        System.out.println(stu);
//
//        Pager<Stu> pager = QueryChain.of(stuMapper)
//                .like(LikeMode.RIGHT,Stu::getName,"木")
//                .paging(Pager.of(1, 10));
//        System.out.println(pager);

        Cursor<Stu> cursor = QueryChain.of(stuMapper)
                .like(LikeMode.RIGHT, Stu::getName, "木")
                .limit(2)
                .cursor();
        cursor.forEach(System.out::println);
    }

    @Test
    public void testKafka1() {
        kafkaProducer.send("kafka-topic-1", "hello,kafka");
    }

}
