package com.ekirov.producerconsumer.producer_service.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResponseReaderService {
//    private final RabbitTemplate rabbitTemplate;
//    public ResponseReaderService(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

//    @Scheduled(fixedRate = 5000) //every 5 seconds
//    public void readResponse() {
//        String response = (String) rabbitTemplate.receiveAndConvert("responseQueue");
//        if(response != null) {
//            System.out.println("Received response: " +response);
//        }
//    }

    //configure RabbitListener to handle multiple messages concurrently with concurrency = "3-5"
    @RabbitListener(queues = "responseQueue")
    public void handleResponse(String response) {
        System.out.println("Producer received response: " + response);
    }
}
