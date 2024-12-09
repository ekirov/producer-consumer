package com.ekirov.producerconsumer.producer_service.services;

import com.ekirov.shared.ResponseMessage;
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
    public void handleResponse(ResponseMessage response) {
        System.out.println("Producer received response: " + response);
        if(response.isSuccess()){
            System.out.println("Success for transaction ID: " + response.getTransactionId());
        } else {
            //persist message status, log errors, send alerts, retry failures, notify other system
            System.out.println("Failure for transaction ID: " + response.getTransactionId());
        }
    }
}
