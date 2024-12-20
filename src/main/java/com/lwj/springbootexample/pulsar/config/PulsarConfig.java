package com.lwj.springbootexample.pulsar.config;

import lombok.SneakyThrows;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class PulsarConfig {


    private final PulsarProperties pulsarProperties;


    public PulsarConfig(PulsarProperties properties) {
        this.pulsarProperties = properties;
    }

    @Bean
    @SneakyThrows
    @ConditionalOnClass(PulsarProperties.class)
    @ConditionalOnProperty(name = "pulsar.service-url",havingValue = "",matchIfMissing = false)
    public PulsarClient pulsarClient(){
        return PulsarClient.builder()
                .serviceUrl(pulsarProperties.getServiceUrl())
                .build();
    }
}
