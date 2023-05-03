package com.flatrcok.order.service.queue.external.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliverCreateRequest {
    private Long orderId;
    private BigDecimal totalPrice;
    private Long customerId;
}
