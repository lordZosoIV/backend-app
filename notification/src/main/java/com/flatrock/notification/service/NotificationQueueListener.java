package com.flatrock.notification.service;

import com.flatrock.notification.model.Notification;
import com.flatrock.notification.model.NotificationRequest;
import com.flatrock.notification.service.facade.NotificationServiceFacade;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationQueueListener {
    private final Gson gson;
    private final NotificationServiceFacade notificationServiceFacade;

    @RabbitListener(queues = "${rabbitmq.notification.queueName}")
    public void receiveMessage(String message) {
        log.info("Received message: " + message);
        String messageBody = new String(message.getBytes(), StandardCharsets.UTF_8);
        Type notificationListType = new TypeToken<List<NotificationRequest>>() {
        }.getType();
        List<NotificationRequest> notifications = gson.fromJson(messageBody, notificationListType);


        notificationServiceFacade.sendNotifications(notifications);
    }

}
