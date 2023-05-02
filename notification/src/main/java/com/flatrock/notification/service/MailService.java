package com.flatrock.notification.service;

import com.flatrock.notification.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MailService {

    public void send(List<Notification> notificationDtoList) {
        notificationDtoList.forEach(notification -> {
            sendMail(notification.getRecipient(), notification.getMessage());
        });
    }

    private void sendMail(String recipient, String message) {
        log.info("Sending EMAIL to " + recipient + " with message: " + message);
    }

}
