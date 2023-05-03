package com.flatrock.packageanddelivery.service;


import com.flatrock.packageanddelivery.entity.DeliveryEntity;
import com.flatrock.packageanddelivery.entity.OrderEntity;
import com.flatrock.packageanddelivery.entity.OrderItemEntity;
import com.flatrock.packageanddelivery.entity.model.DeliveryStatus;
import com.flatrock.packageanddelivery.model.DeliverCreateRequest;
import com.flatrock.packageanddelivery.model.Orders;
import com.flatrock.packageanddelivery.repository.DeliveryRepository;
import com.flatrock.packageanddelivery.repository.OrderItemRepository;
import com.flatrock.packageanddelivery.repository.OrderRepository;
import com.flatrock.packageanddelivery.specification.DeliverySpecifications;
import com.flatrock.packageanddelivery.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public DeliveryEntity startDeliver(DeliverCreateRequest request) {
        OrderEntity order = createOrder(request);
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        deliveryEntity.setOrder(order);
        deliveryEntity.setStatus(DeliveryStatus.PENDING);
        return deliveryRepository.save(deliveryEntity);
    }

    public OrderEntity createOrder(DeliverCreateRequest request) {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(request.getOrderId());
        orderEntity.setCustomerId(request.getCustomerId());
        orderEntity.setTotalPrice(request.getTotalPrice());

        orderEntity = orderRepository.save(orderEntity); // save orderEntity to generate id value

        for (Orders item : request.getOrderItems()) {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setProductId(item.getProductId());
            orderItemEntity.setQuantity(item.getQuantity());
            orderItemEntity.setSellerId(item.getSellerId());
            orderItemEntity.setOrder(orderEntity);
            orderItemEntities.add(orderItemEntity);
        }

        orderEntity.setOrderItems(orderItemEntities);
        return orderRepository.save(orderEntity);
    }

    public DeliveryEntity get(Long id) {
        return deliveryRepository
                .findById(id)
                .orElseThrow(() -> HandledExceptionFactory.getHandledException("not founded delivery"));
    }

    public DeliveryEntity updateStatus(Long deliverId, DeliveryStatus status) {
        DeliveryEntity deliveryEntity = get(deliverId);
        deliveryEntity.setStatus(status);
        return deliveryRepository.save(deliveryEntity);
    }

    public List<DeliveryEntity> filter(DeliveryStatus status,
                                       BigDecimal totalPriceFrom, BigDecimal totalPriceTo,
                                       Timestamp timeFrom, Timestamp timeTo) {
        Specification<DeliveryEntity> specification = Specification
                .where(DeliverySpecifications.hasStatus(status))
                .and(DeliverySpecifications.totalPriceBetween(totalPriceFrom, totalPriceTo))
                .and(DeliverySpecifications.deliveredAfter(timeFrom))
                .and(DeliverySpecifications.deliveredBefore(timeTo));

        return deliveryRepository.findAll(specification);
    }
}
