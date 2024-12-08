package com.ekirov.producerconsumer.producer_service.controllers;

import com.ekirov.producerconsumer.producer_service.services.ProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/producer")
public class ProducerController {
    private final ProducerService producerService;
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        producerService.sendMessage(message);
        return ResponseEntity.ok("Message sent: "+message);
    }
    
}
