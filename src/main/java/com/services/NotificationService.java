package com.services;

import com.helpers.Notification;
import com.helpers.NotificationType;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Nick on 16.06.17.
 */
public class NotificationService extends Exception {

    public NotificationService(String message) {
        super(message);
    }

    public void show(HttpServletRequest req, NotificationType messageType)
    {
        Notification notification = new Notification(this.getMessage(),messageType.getType());
        req.setAttribute("message",notification);
    }

}
