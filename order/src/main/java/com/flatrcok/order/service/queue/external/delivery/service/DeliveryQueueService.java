package com.flatrcok.order.service.queue.external.delivery.service;


import com.flatrcok.order.config.RabbitMQConfig;
import com.flatrcok.order.service.queue.external.delivery.model.DeliverCreateRequest;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryQueueService {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfig rabbitMQConfig;
    private final Gson gson;

    public void createPendingDelivery(DeliverCreateRequest OrderCreateRequest) {
        String json = gson.toJson(OrderCreateRequest);
        logOutgoingMessage(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getPackingAndDeliverRoutingKey(), json);
        rabbitTemplate.convertAndSend(rabbitMQConfig.getExchangeName(), rabbitMQConfig.getPackingAndDeliverRoutingKey(), json);
    }

    private void logOutgoingMessage(String exchange, String routingKey, String message) {
        log.info("Sending message to exchange: {}, routing key: {}, message: {}", exchange, routingKey, message);
    }

}
