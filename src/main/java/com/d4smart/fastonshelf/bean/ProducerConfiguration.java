package com.d4smart.fastonshelf.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qunar.tc.qmq.MessageProducer;
import qunar.tc.qmq.producer.MessageProducerProvider;

@Configuration
public class ProducerConfiguration {

    @Bean
    public MessageProducer producer() {
        MessageProducerProvider provider = new MessageProducerProvider();
        provider.setAppCode("your app");
        provider.setMetaServer("your meta address");
        return provider;
    }
}
