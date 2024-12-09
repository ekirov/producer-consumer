package com.ekirov.shared;

public class Message {
    private String transactionId;
    private String messageType;
    private String payload;

    public Message(){}
    public Message(String messageType, String payload) {
        this.messageType = messageType;
        this.payload = payload;
    }

    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getMessageType() {
        return messageType;
    }
    public void setMessageType(String messageType) {
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