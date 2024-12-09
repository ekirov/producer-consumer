package com.ekirov.producerconsumer.consumer_service.services;

import com.ekirov.producerconsumer.consumer_service.configs.RabbitMQProperties;
import com.ekirov.shared.Message;
import com.ekirov.shared.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Service class for reading messages from RabbitMQ and sending responses to RabbitMQ
 */
@Service
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
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
        logger.info("Thread ID: {} - Consumer received message: {}", Thread.currentThread().getId(), message);

        try{
            //process the message for response by the messageType
            ResponseMessage response = switch (message.getMessageType()) {
                case TYPE_A -> new ResponseMessage(message.getTransactionId(), true, "Processed TYPE_A successfully");
                case TYPE_B -> new ResponseMessage(message.getTransactionId(), false, "Processed TYPE_B as failure");
                default -> new ResponseMessage(message.getTransactionId(), false, "Unknown message type");
            };
            logger.debug("Generated response for transaction ID {}: {}", message.getTransactionId(), response);

            //send the response to RabbitMQ response queue
            rabbitTemplate.convertAndSend(rabbitMQProperties.getResponseQueue(), response);
            logger.info("Consumer sent response for transaction ID {}: {}", message.getTransactionId(), response);
        } catch (Exception e){
            logger.error("Error processing message with transaction ID {}: {}", message.getTransactionId(), message, e);
        }

    }
}
