package com.ekirov.producerconsumer.producer_service.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/producer")
public class ProducerController {
    private final RabbitTemplate rabbitTemplate;
    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        System.out.println("Sending message: " + message);
        rabbitTemplate.convertAndSend("requestQueue", message);
        return ResponseEntity.ok("Message sent: "+message);
    }
    
}
