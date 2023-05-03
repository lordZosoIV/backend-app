package com.flatrock.packageanddelivery.service;

import com.flatrock.packageanddelivery.model.DeliverCreateRequest;
import com.flatrock.packageanddelivery.service.facade.DeliveryServiceFacade;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryQueueListener {
    private final Gson gson;
    private final DeliveryServiceFacade deliveryServiceFacade;

    @RabbitListener(queues = "${rabbitmq.packingAndDeliver.queueName}")
    public void receiveMessage(String message) {
        log.info("Received message: " + message);
        String messageBody = new String(message.getBytes(), StandardCharsets.UTF_8);
        DeliverCreateRequest request = gson.fromJson(messageBody, DeliverCreateRequest.class);
        deliveryServiceFacade.startDeliver(request);
    }
}
