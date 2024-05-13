package com.enoca.challenge.error;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {
    
    String status;
    String message;
    long timestamp;

    public ErrorMessage()
    {

    }

    public ErrorMessage(String status, String message)
    {
        this.status = status;
        this.message = message;
        this.timestamp = new Date().getTime();
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setAll(String status, String message)
    {
        this.status = status;
        this.message = message;
        this.timestamp = new Date().getTime();
    }
    
    
}
