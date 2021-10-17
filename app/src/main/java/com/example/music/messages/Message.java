package com.example.music.messages;

public class Message {
    String message;
    boolean currentUser;


    public Message(String message, boolean currentUser) {
        this.message=message;
        this.currentUser=currentUser;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(boolean currentUser) {
        this.currentUser = currentUser;
    }
}