package com.flatrock.packageanddelivery.service.facade;

import com.flatrock.packageanddelivery.entity.model.DeliveryStatus;
import com.flatrock.packageanddelivery.model.DeliverCreateRequest;
import com.flatrock.packageanddelivery.model.DeliverResponse;
import com.flatrock.packageanddelivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceFacade {
    private final DeliveryService service;

    public List<DeliverResponse> filter(DeliveryStatus status,
                                        BigDecimal totalPriceFrom, BigDecimal totalPriceTo,
                                        Timestamp timeFrom, Timestamp timeTo) {
        return service.filter(status, totalPriceFrom, totalPriceTo, timeFrom, timeTo)
                .stream()
                .map(DeliverResponse::transform)
                .toList();
    }

    public DeliverResponse updateStatus(Long deliveryId, DeliveryStatus status) {
        return DeliverResponse.transform(service.updateStatus(deliveryId, status));
    }

    public void startDeliver(DeliverCreateRequest request) {
        service.startDeliver(request);
    }
}
