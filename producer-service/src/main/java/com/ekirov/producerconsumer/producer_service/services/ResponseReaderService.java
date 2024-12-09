package com.ekirov.producerconsumer.producer_service.services;

import com.ekirov.shared.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Service class for reading and processing from the RabbitMQ responseQueue
 */
@Service
public class ResponseReaderService {

    private static final Logger logger = LoggerFactory.getLogger(ResponseReaderService.class);
    /**
     * Listens for messages on the RabbitMQ responseQueue.
     *
     * @param response The message received.
     */
    @RabbitListener(queues = "${rabbitmq.response-queue}")
    public void handleResponse(ResponseMessage response) {
        logger.info("Producer received response: {}", response);
        if(response.isSuccess()){
            logger.info("SUCCESS for transaction ID: {}", response.getTransactionId());
        } else {
            //persist message status, log errors, send alerts, retry failures, notify other system
            logger.warn("FAILURE for transaction ID: {}", response.getTransactionId());
        }
    }
}
