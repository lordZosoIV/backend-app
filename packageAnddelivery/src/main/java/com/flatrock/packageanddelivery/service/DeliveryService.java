package com.flatrock.packageanddelivery.service;


import com.flatrock.packageanddelivery.entity.DeliveryEntity;
import com.flatrock.packageanddelivery.entity.model.DeliveryStatus;
import com.flatrock.packageanddelivery.model.DeliverCreateRequest;
import com.flatrock.packageanddelivery.repository.DeliveryRepository;
import com.flatrock.packageanddelivery.specification.DeliverySpecifications;
import com.flatrock.packageanddelivery.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryEntity startDeliver(DeliverCreateRequest request) {
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        deliveryEntity.setAmount(request.getTotalPrice());
        deliveryEntity.setOrderId(request.getOrderId());
        deliveryEntity.setStatus(DeliveryStatus.PENDING);
        return deliveryRepository.save(deliveryEntity);
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
