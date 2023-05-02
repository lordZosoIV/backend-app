package com.flatrcok.order.service.facade;

import com.flatrcok.order.model.request.OrderRequest;
import com.flatrcok.order.service.OrderService;
import com.flatrcok.order.service.queue.external.notification.model.Notification;
import com.flatrcok.order.service.queue.external.notification.service.NotificationQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceFacade {

    private final OrderService orderService;
    private final NotificationQueueService notificationQueueService;

    public void createOrder(OrderRequest request) {
        orderService.createOrder(request);
        notificationQueueService.sendMessage(List.of(new Notification("TEST MSG", "TEST USER")));

    }

}
