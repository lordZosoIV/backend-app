package com.flatrock.packageanddelivery.model;

import com.flatrock.packageanddelivery.entity.model.DeliveryStatus;
import com.flatrock.packageanddelivery.entity.DeliveryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliverResponse {
    private Long id;
    private DeliveryStatus status;
    private Long orderId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private BigDecimal amount;

    public static DeliverResponse transform(DeliveryEntity deliveryEntity) {
        return new DeliverResponse(
                deliveryEntity.getId(),
                deliveryEntity.getStatus(),
                deliveryEntity.getOrderId(),
                deliveryEntity.getCreatedAt(),
                deliveryEntity.getUpdatedAt(),
                deliveryEntity.getAmount()
        );
    }
}
