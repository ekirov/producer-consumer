package com.ekirov.shared;

import com.ekirov.shared.enums.MessageType;

public class Message {
    private String transactionId;
    private MessageType messageType;
    private String payload;

    public Message(){}
    public Message(MessageType messageType, String payload) {
        this.messageType = messageType;
        this.payload = payload;
    }

    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public MessageType getMessageType() {
        return messageType;
    }
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
    public String getPayload() {
        return payload;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }
    public String toString(){
        return "Message {" +
                "transactionId='"+transactionId+'\''+
                ", messageType='"+messageType+'\''+
                ", payload='"+payload+'\''+
                '}';
    }
}