package com.flatrock.notification.service;

import com.flatrock.notification.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SMSService {

    public void send(List<Notification> notificationDtoList) {
        notificationDtoList.forEach(notification -> {
            send(notification.getRecipient(), notification.getMessage());
        });
    }

    private void send(String recipient, String message) {
        log.info("Sending SMS to " + recipient + " with message: " + message);
    }
}
