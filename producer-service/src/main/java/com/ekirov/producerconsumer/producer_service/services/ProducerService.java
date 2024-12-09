package com.ekirov.producerconsumer.producer_service.services;

import com.ekirov.producerconsumer.producer_service.configs.RabbitMQProperties;
import com.ekirov.shared.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class for sending messages to RabbitMQ requestQueue
 */
@Service
public class ProducerService {
    private final RabbitMQProperties rabbitMQProperties;
    private final RabbitTemplate rabbitTemplate;

    /**
     * Constructor: injects RabbitTemplate and RabbitMQProperties.
     *
     * @param rabbitTemplate The RabbitTemplate for interacting with RabbitMQ.
     * @param rabbitMQProperties contains RabbitMQ queue names.
     */
    public ProducerService(RabbitMQProperties rabbitMQProperties, RabbitTemplate rabbitTemplate) {
        this.rabbitMQProperties = rabbitMQProperties;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Sends message to the RabbitMQ requestQueue with a random transaction ID.
     *
     * @param message The message object containing messageType and payload.
     */
    public void sendMessage(Message message) {
        message.setTransactionId(UUID.randomUUID().toString());
        System.out.println("Producer sending request: " + message);
        try{
            rabbitTemplate.convertAndSend(
                    rabbitMQProperties.getRequestQueue(),
                    message
            );
        } catch(Exception e){
            System.err.println("Failed to send message to RabbitMQ: " +e.getMessage());
            throw new RuntimeException("RabbitMQ is unavailable");
        }

    }
}
