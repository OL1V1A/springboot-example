package com.lwj.springbootexample.pulsar;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pulsar")
@Data
public class PulsarProperties {

    private String serviceUrl;

    private String topic;

}

