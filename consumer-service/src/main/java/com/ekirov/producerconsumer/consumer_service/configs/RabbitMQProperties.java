package com.ekirov.producerconsumer.consumer_service.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQProperties {
    private String requestQueue;
    private String responseQueue;

    public String getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(String requestQueue) {
        this.requestQueue = requestQueue;
    }

    public String getResponseQueue() {
        return responseQueue;
    }

    public void setResponseQueue(String responseQueue) {
        this.responseQueue = responseQueue;
    }
}
