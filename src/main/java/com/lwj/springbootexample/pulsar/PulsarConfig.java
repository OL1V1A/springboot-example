package com.lwj.springbootexample.pulsar;

import lombok.SneakyThrows;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class PulsarConfig {

    @Resource
    private PulsarProperties pulsarProperties;

    @Bean
    @SneakyThrows
    public PulsarClient pulsarClient(){
        return PulsarClient.builder()
                .serviceUrl(pulsarProperties.getServiceUrl())
                .build();
    }
}
