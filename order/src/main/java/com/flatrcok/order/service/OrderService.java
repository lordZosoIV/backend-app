package com.flatrcok.order.service;

import com.flatrcok.order.entity.OrderEntity;
import com.flatrcok.order.entity.OrderItemEntity;
import com.flatrcok.order.model.OrderItem;
import com.flatrcok.order.model.request.OrderRequest;
import com.flatrcok.order.repository.OrderItemRepository;
import com.flatrcok.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderReposity;
    private final OrderItemRepository orderItemRepository;

    public OrderEntity createOrder(OrderRequest request, Long customerId) {
        List<OrderItemEntity> result = new ArrayList<>();
        OrderEntity order = OrderEntity.builder()
                .customerId(customerId) // TODO need phone
                .build();
        order = orderReposity.save(order);
        for (OrderItem item : request.getOrderItems()) {
            OrderItemEntity entity = OrderItemEntity.builder()
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .order(order)
                    .sellerId(100L) //TODO "DUMMY NEED TO IMPELEMENT"
                    .build();
            result.add(entity);
        }

        result = orderItemRepository.saveAll(result);
        order.setOrderItems(result);


        return orderReposity.save(order);
    }

}
