package com.ekirov.producerconsumer.producer_service.services;

import com.ekirov.producerconsumer.producer_service.configs.RabbitMQProperties;
import com.ekirov.shared.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class for sending messages to RabbitMQ requestQueue
 */
@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);
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

        try{
            logger.info("Producer sending request: {}", message);
            rabbitTemplate.convertAndSend(
                    rabbitMQProperties.getRequestQueue(),
                    message
            );
            logger.info("Message sent successfully with transaction id {} ", message.getTransactionId());
        } catch(Exception e){
            logger.error("Failed to send message to RabbitMQ: {}", message, e);
            throw new RuntimeException("RabbitMQ is unavailable");
        }

    }
}
