package com.flatrock.product.service;

import com.flatrock.product.model.PurchaseRequest;
import com.flatrock.product.service.facade.ProductServiceFacade;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductQueueListener {
    private final Gson gson;
    private final ProductServiceFacade productServiceFacade;

    @RabbitListener(queues = "${rabbitmq.product.queueName}")
    public void receiveMessage(String message) {
        log.info("Received message: " + message);
        String messageBody = new String(message.getBytes(), StandardCharsets.UTF_8);
        Type decrementStocksDtoListType = new TypeToken<List<PurchaseRequest>>() {
        }.getType();
        List<PurchaseRequest> purchaseRequests = gson.fromJson(messageBody, decrementStocksDtoListType);
        productServiceFacade.decrementQuantity(purchaseRequests);
    }
}
