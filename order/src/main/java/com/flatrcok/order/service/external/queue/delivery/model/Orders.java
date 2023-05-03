package com.flatrcok.order.service.external.queue.delivery.model;

import com.flatrcok.order.entity.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    private Long id;
    private Long productId;
    private Integer quantity;
    private Long sellerId;

    public static Orders transform(OrderItemEntity entity) {
        return new Orders(
                entity.getId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getSellerId()
        );
    }
}
