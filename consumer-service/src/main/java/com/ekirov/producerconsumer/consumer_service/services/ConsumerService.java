package com.ekirov.producerconsumer.consumer_service.services;

import com.ekirov.producerconsumer.consumer_service.configs.RabbitMQConfig;
import com.ekirov.producerconsumer.consumer_service.configs.RabbitMQProperties;
import com.ekirov.shared.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;
    public ConsumerService(RabbitTemplate rabbitTemplate, RabbitMQProperties rabbitMQProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    @RabbitListener(queues = "#{rabbitMQProperties.requestQueue}")
    public void processMessage(Message message) {
//        Thread thread = Thread.currentThread();
//        System.out.println("Thread id: "+thread.getId());
        System.out.println("Consumer received request: " + message);
        String response = switch (message.getMessageType()) {
            case "TYPE_A" -> "Processed TYPE_A with transaction ID: " + message.getTransactionId();
            case "TYPE_B" -> "Processed TYPE_B with payload: " + message.getPayload();
            default -> "Unknown message type for transaction ID: " + message.getTransactionId();
        };
        rabbitTemplate.convertAndSend(rabbitMQProperties.getResponseQueue(), response);
    }
}
