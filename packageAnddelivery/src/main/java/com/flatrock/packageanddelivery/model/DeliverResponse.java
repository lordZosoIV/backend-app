package com.flatrock.packageanddelivery.model;

import com.flatrock.packageanddelivery.entity.DeliveryEntity;
import com.flatrock.packageanddelivery.entity.model.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliverResponse {
    private Long id;
    private DeliveryStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long orderId;
    private BigDecimal amount;
    private List<Orders> orders;

    public static DeliverResponse transform(DeliveryEntity deliveryEntity) {
        return new DeliverResponse(
                deliveryEntity.getId(),
                deliveryEntity.getStatus(),
                deliveryEntity.getCreatedAt(),
                deliveryEntity.getUpdatedAt(),
                deliveryEntity.getOrder().getOrderId(),
                deliveryEntity.getOrder().getTotalPrice(),
                deliveryEntity.getOrder().getOrderItems().stream().map(Orders::transform).collect(Collectors.toList())
        );
    }
}
