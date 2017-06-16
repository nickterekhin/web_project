package com.helpers;

/**
 * Created by Nick on 16.06.17.
 */
public class Notification {
    private String message;
    private String type;

    public Notification(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
