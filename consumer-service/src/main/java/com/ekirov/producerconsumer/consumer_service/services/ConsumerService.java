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
        rabbitTemplate.convertAndSend(rabbitMQProperties.getResponseQueue(), "Consumer processed: " +message);
    }
}
