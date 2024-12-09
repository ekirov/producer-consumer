package com.ekirov.producerconsumer.consumer_service.services;

import com.ekirov.producerconsumer.consumer_service.configs.RabbitMQProperties;
import com.ekirov.shared.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Service class for reading messages from RabbitMQ and sending responses to RabbitMQ
 */
@Service
public class ConsumerService {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties rabbitMQProperties;

    /**
     * Constructor injects RabbitTemplate and RabbitMQProperties
     * @param rabbitTemplate used to send messages
     * @param rabbitMQProperties contains RabbitMQ queue names
     */
    public ConsumerService(RabbitTemplate rabbitTemplate, RabbitMQProperties rabbitMQProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQProperties = rabbitMQProperties;
    }

    /**
     * Listens for messages on RabbitMQ request queue
     * Send messages on RabbitMQ response queue
     * Method also allows for multithreading via the RabbitListener annotation
     * @param message incoming message containing transactionID, messageType, and payload
     */
    @RabbitListener(queues = "#{rabbitMQProperties.requestQueue}", concurrency = "3")
    public void processMessage(Message message) {
        //log thread info for debugging
        System.out.println("Thread id: "+Thread.currentThread().getId());
        System.out.println("Consumer received request: " + message);

        //process the message for response by the messageType
        String response = switch (message.getMessageType()) {
            case TYPE_A -> "Processed TYPE_A with transaction ID: " + message.getTransactionId();
            case TYPE_B -> "Processed TYPE_B with payload: " + message.getPayload();
            default -> "Unknown message type for transaction ID: " + message.getTransactionId();
        };

        //send the response to RabbitMQ response queue
        rabbitTemplate.convertAndSend(rabbitMQProperties.getResponseQueue(), response);

        System.out.println("Consumer sending response: "+response);
    }
}
