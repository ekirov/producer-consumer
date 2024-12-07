package com.ekirov.producerconsumer.consumer_service.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue requestQueue() {
        return new Queue("requestQueue");
    }

    @Bean
    public Queue responseQueue() {
        return new Queue("responseQueue");
    }
}
