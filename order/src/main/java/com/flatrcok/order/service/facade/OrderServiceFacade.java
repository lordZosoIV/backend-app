package com.flatrcok.order.service.facade;

import com.flatrcok.order.entity.OrderEntity;
import com.flatrcok.order.model.request.OrderRequest;
import com.flatrcok.order.service.OrderService;
import com.flatrcok.order.service.queue.external.delivery.model.DeliverCreateRequest;
import com.flatrcok.order.service.queue.external.delivery.service.DeliveryQueueService;
import com.flatrcok.order.service.queue.external.notification.service.NotificationQueueService;
import com.flatrcok.order.service.queue.external.product.service.ProductQueueService;
import com.flatrcok.order.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceFacade {

    private final OrderService orderService;
    private final NotificationQueueService notificationQueueService;
    private final ProductQueueService productQueueService;
    private final DeliveryQueueService deliveryQueueService;

    public void createOrder(OrderRequest request) {
        OrderEntity order = orderService.createOrder(request, SecurityUtils.getAuthenticatedUserId());

//        productQueueService.decrementQuantity(request.getOrderItems().stream().map(PurchaseRequest::transform).toList());
//        notificationQueueService.sendMessage(List.of(new Notification("TEST MSG", "TEST USER"))); // TODO dummy
        deliveryQueueService.createPendingDelivery(new DeliverCreateRequest(order.getId(), new BigDecimal("100"), order.getCustomerId()));
    }

}
