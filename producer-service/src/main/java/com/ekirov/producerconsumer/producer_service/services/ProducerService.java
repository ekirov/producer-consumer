package com.ekirov.producerconsumer.producer_service.services;

import com.ekirov.producerconsumer.producer_service.configs.RabbitMQProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private final RabbitMQProperties rabbitMQProperties;
    private final RabbitTemplate rabbitTemplate;

    public ProducerService(RabbitMQProperties rabbitMQProperties, RabbitTemplate rabbitTemplate) {
        this.rabbitMQProperties = rabbitMQProperties;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
        rabbitTemplate.convertAndSend(
                rabbitMQProperties.getRequestQueue(),
                message
        );
    }
}
