package com.boilerr.coffeetablerest.dto;

public class InboxMessage {
    private final long id;
    private final String timestamp ;
    private final String message ;

    public InboxMessage(long id, String timestamp, String message) {
        this.id = id;
        this.timestamp = timestamp;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
