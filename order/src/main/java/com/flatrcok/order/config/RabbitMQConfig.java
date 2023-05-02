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
    private final String packingAndDeliverQueueName;
    private final String packingAndDeliverRoutingKey;
    private final String productQueueName;
    private final String productRoutingKey;

    public RabbitMQConfig(
            @Value("${rabbitmq.exchangeName}") String exchangeName,
            @Value("${rabbitmq.notification.queueName}") String notificationQueueName,
            @Value("${rabbitmq.notification.routingKey}") String notificationRoutingKey,
            @Value("${rabbitmq.packingAndDeliver.queueName}") String padQueueName,
            @Value("${rabbitmq.packingAndDeliver.routingKey}") String padRoutingKey,
            @Value("${rabbitmq.product.queueName}") String productQueueName,
            @Value("${rabbitmq.product.routingKey}") String productRoutingKey
    ) {
        this.exchangeName = exchangeName;
        this.notificationQueueName = notificationQueueName;
        this.notificationRoutingKey = notificationRoutingKey;
        this.packingAndDeliverQueueName = padQueueName;
        this.packingAndDeliverRoutingKey = padRoutingKey;
        this.productQueueName = productQueueName;
        this.productRoutingKey = productRoutingKey;
    }


}