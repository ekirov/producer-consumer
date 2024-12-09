package com.ekirov.producerconsumer.producer_service.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config class for RabbitMQ
 * Defines beans for managing queues.
 */
@Configuration
public class RabbitMQConfig {

    private final RabbitMQProperties rabbitMQProperties;

    /**
     * Constructor injects RabbitMQProperties
     * @param rabbitMQProperties contains RabbitMQ queue names
     */
    public RabbitMQConfig(RabbitMQProperties rabbitMQProperties) {
        this.rabbitMQProperties = rabbitMQProperties;
    }

    /**
     * Define the request queue which is used to send messages to consumer
     * @return request queue
     */
    @Bean
    public Queue requestQueue() {
        return new Queue(rabbitMQProperties.getRequestQueue());
    }

    /**
     * Define the response queue which is used to read messages from consumer
     * @return response queue
     */
    @Bean
    public Queue responseQueue() {
        return new Queue(rabbitMQProperties.getResponseQueue());
    }

    /**
     * Configures a Jackson-based message converter to allow JSON for RabbitMQ communication
     * @return Jackson2JsonMessageConverter instance
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Configures RabbitTemplate to use Jackson message converter
     * @param connectionFactory connection factory for RabbitMQ
     * @return the configured RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
