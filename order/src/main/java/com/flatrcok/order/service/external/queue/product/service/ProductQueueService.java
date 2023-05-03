package com.flatrcok.order.service.external.queue.product.service;

import com.flatrcok.order.config.RabbitMQConfig;
import com.flatrcok.order.service.external.queue.product.model.PurchaseRequest;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductQueueService {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;
    private final Gson gson;

    public void decrementQuantity(List<PurchaseRequest> orderItems) {
        String json = gson.toJson(orderItems);
        logOutgoingMessage(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getProductRoutingKey(), json);
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getProductRoutingKey(), json);
    }

    private void logOutgoingMessage(String exchange, String routingKey, String message) {
        log.info("Sending message to exchange: {}, routing key: {}, message: {}", exchange, routingKey, message);
    }

}
