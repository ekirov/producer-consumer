package com.ekirov.producerconsumer.producer_service.controllers;

import com.ekirov.producerconsumer.producer_service.services.ProducerService;
import com.ekirov.shared.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for producer endpoints.
 */
@RestController
@RequestMapping("/producer")
public class ProducerController {
    private final ProducerService producerService;

    /**
     * Constructor: injects ProducerService
     * @param producerService service that handles message queuing.
     */
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    /**
     * Endpoint to send a message to the RabbitMQ requestQueue.
     * Accepts a payload from the client, which is forwarded to ProducerService
     * @param message the payload containing messageType and payload.
     * @return ResponseEntity containing the confirmation of the sent message
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        producerService.sendMessage(message);
        return ResponseEntity.ok("Message sent: "+message);
    }
    
}
