package com.flatrcok.order.service.facade;

import com.flatrcok.order.entity.OrderEntity;
import com.flatrcok.order.entity.OrderItemEntity;
import com.flatrcok.order.exception.HandledException;
import com.flatrcok.order.model.OrderItem;
import com.flatrcok.order.model.request.OrderRequest;
import com.flatrcok.order.service.OrderService;
import com.flatrcok.order.service.external.product.Product;
import com.flatrcok.order.service.external.product.ProductApiService;
import com.flatrcok.order.service.external.queue.delivery.model.DeliverCreateRequest;
import com.flatrcok.order.service.external.queue.delivery.model.Orders;
import com.flatrcok.order.service.external.queue.delivery.service.DeliveryQueueService;
import com.flatrcok.order.service.external.queue.notification.model.Notification;
import com.flatrcok.order.service.external.queue.notification.service.NotificationQueueService;
import com.flatrcok.order.service.external.queue.product.model.PurchaseRequest;
import com.flatrcok.order.service.external.queue.product.service.ProductQueueService;
import com.flatrcok.order.util.HandledExceptionFactory;
import com.flatrcok.order.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceFacade {

    private final OrderService orderService;
    private final NotificationQueueService notificationQueueService;
    private final ProductQueueService productQueueService;
    private final DeliveryQueueService deliveryQueueService;
    private final ProductApiService productApiService;

    public void createOrder(OrderRequest request) {
        List<Long> productIds = request.getOrderItems().stream().map(OrderItem::getProductId).toList();
        List<Product> products = productApiService.filter(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        checkQuantity(request.getOrderItems(), productMap);

        OrderEntity order = orderService.createOrder(request, SecurityUtils.getAuthenticatedUserId());

        productQueueService.decrementQuantity(request.getOrderItems().stream().map(PurchaseRequest::transform).toList());


        BigDecimal totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        deliveryQueueService.createPendingDelivery(new DeliverCreateRequest(order.getId(), totalPrice, order.getCustomerId(),
                order.getOrderItems().stream().map(Orders::transform).collect(Collectors.toList())));


        Notification sellerNotification = new Notification("order product ids: " + order.getOrderItems().stream().map(OrderItemEntity::getProductId).toList(),"EMAIL",
                productMap.get(request.getOrderItems().get(0).getProductId()).getSellerEmail());

        Notification clientNotification = new Notification("order id:" + order.getId() + " with price:" +
                totalPrice, "SMS", request.getBuyerPhone());

        notificationQueueService.sendMessage(List.of(sellerNotification, clientNotification));

    }

    private void checkQuantity(List<OrderItem> orderItems,  Map<Long, Product> productMap) {

        for (OrderItem orderItem : orderItems) {
            Long productId = orderItem.getProductId();
            Product product = productMap.get(productId);
            if (product == null) {
                 throw HandledExceptionFactory.getHandledException("Invalid product ID: " + productId);
            }
            if (orderItem.getQuantity() > product.getQuantity()) {
                throw HandledExceptionFactory.getHandledException("Invalid product ID: " + productId);
            }
        }
    }


}
