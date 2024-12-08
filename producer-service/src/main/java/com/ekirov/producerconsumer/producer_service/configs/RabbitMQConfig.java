package com.ekirov.producerconsumer.producer_service.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final RabbitMQProperties rabbitMQProperties;
    public RabbitMQConfig(RabbitMQProperties rabbitMQProperties) {
        this.rabbitMQProperties = rabbitMQProperties;
    }
    @Bean
    public Queue requestQueue() {
        return new Queue(rabbitMQProperties.getRequestQueue());
    }

    @Bean
    public Queue responseQueue() {
        return new Queue(rabbitMQProperties.getResponseQueue());
    }
}
