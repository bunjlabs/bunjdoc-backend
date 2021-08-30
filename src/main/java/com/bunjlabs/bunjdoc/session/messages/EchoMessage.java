package com.bunjlabs.bunjdoc.session.messages;

public class EchoMessage extends Message {
    private String message;

    public EchoMessage() {
    }

    public EchoMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
