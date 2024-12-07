package com.ekirov.producerconsumer.consumer_service.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final RabbitTemplate rabbitTemplate;
    public ConsumerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "requestQueue")
    public void processMessage(String message) {
        System.out.println("Consumer received request: " + message);
        rabbitTemplate.convertAndSend("responseQueue", "Consumer processed: " +message);
    }
}
