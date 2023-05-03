package com.flatrock.packageanddelivery.model;

import com.flatrock.packageanddelivery.entity.OrderItemEntity;
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

    public static Orders transform(OrderItemEntity d) {
        return new Orders(d.getId(), d.getProductId(), d.getQuantity(), d.getSellerId());
    }
}
