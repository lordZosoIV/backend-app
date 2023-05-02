package com.flatrcok.order.service.queue.external.product.model;

import com.flatrcok.order.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseRequest {
    private Long id;
    private Integer quantity;

    public static PurchaseRequest transform(OrderItem orderItem) {
        return new PurchaseRequest(orderItem.getProductId(), orderItem.getQuantity());
    }

}
