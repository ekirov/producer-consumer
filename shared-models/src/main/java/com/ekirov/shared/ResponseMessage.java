package com.ekirov.shared;

public class ResponseMessage {
    private String transactionId;
    private boolean success;
    private String details;

    public ResponseMessage(String transactionId, boolean success, String details) {
        this.transactionId = transactionId;
        this.success = success;
        this.details = details;
    }

    public ResponseMessage() {}

    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString(){
        return "ResponseMessage{" +
                "transactionId='" + transactionId + '\'' +
                ", success=" + success +
                ", details='" + details + '\'' +
                '}';
    }
}