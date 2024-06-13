package com.vedha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class AppConfig {

    // uses default message channel if not specified is DirectChannel
    @Bean(name = "orderChannel")
    public MessageChannel orderChannel() {

        // DirectChannel is a default message channel type that does not store messages in memory before blocking
        // and is used for point-to-point messaging between components in the same JVM
        return new DirectChannel();
//        return new QueueChannel(10);
    }

    @Bean(name = "processOrderChannel")
    public MessageChannel processOrderChannel() {

        // QueueChannel is a message channel type that can store up to 10 messages in memory before blocking
//        return new QueueChannel(10);
        return new DirectChannel();
    }

    @Bean(name = "replyChannel")
    public MessageChannel replyChannel() {

        // QueueChannel is a message channel type that can store up to 10 messages in memory before blocking
//        return new QueueChannel(10);
        return new DirectChannel();
    }
}
