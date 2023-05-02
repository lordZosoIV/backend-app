package com.flatrcok.order.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RabbitMQConfig {
    private final String exchangeName;
    private final String notificationQueueName;
    private final String notificationRoutingKey;
    private final String padQueueName;
    private final String padRoutingKey;
    private final String productQueueName;
    private final String productRoutingKey;

    public RabbitMQConfig(
            @Value("${rabbitmq.exchangeName}") String exchangeName,
            @Value("${rabbitmq.notification.queueName}") String notificationQueueName,
            @Value("${rabbitmq.notification.routingKey}") String notificationRoutingKey,
            @Value("${rabbitmq.pad.queueName}") String padQueueName,
            @Value("${rabbitmq.pad.routingKey}") String padRoutingKey,
            @Value("${rabbitmq.product.queueName}") String productQueueName,
            @Value("${rabbitmq.product.routingKey}") String productRoutingKey
    ) {
        this.exchangeName = exchangeName;
        this.notificationQueueName = notificationQueueName;
        this.notificationRoutingKey = notificationRoutingKey;
        this.padQueueName = padQueueName;
        this.padRoutingKey = padRoutingKey;
        this.productQueueName = productQueueName;
        this.productRoutingKey = productRoutingKey;
    }


}