package com.ekirov.producerconsumer.producer_service.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Service class for reading and processing from the RabbitMQ responseQueue
 */
@Service
public class ResponseReaderService {

    /**
     * Listens for messages on the RabbitMQ responseQueue.
     *
     * @param response The message received.
     */
    @RabbitListener(queues = "${rabbitmq.response-queue}")
    public void handleResponse(String response) {
        System.out.println("Producer received response: " + response);
    }
}
