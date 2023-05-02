package com.flatrcok.order.service.queue.external.notification.service;

import com.flatrcok.order.config.RabbitMQConfig;
import com.flatrcok.order.service.queue.external.notification.model.Notification;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationQueueService {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;
    private final Gson gson;

    public void sendMessage(List<Notification> notificationDtos) {
        String json = gson.toJson(notificationDtos);
        logOutgoingMessage(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getNotificationRoutingKey(), json);
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getNotificationRoutingKey(), json);
    }

    private void logOutgoingMessage(String exchange, String routingKey, String message) {
        log.info("Sending message to exchange: {}, routing key: {}, message: {}", exchange, routingKey, message);
    }

}