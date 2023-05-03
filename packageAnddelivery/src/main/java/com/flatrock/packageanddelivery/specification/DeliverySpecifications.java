package com.flatrock.packageanddelivery.specification;


import com.flatrock.packageanddelivery.entity.DeliveryEntity;
import com.flatrock.packageanddelivery.entity.model.DeliveryStatus;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DeliverySpecifications {
    public static Specification<DeliveryEntity> hasStatus(DeliveryStatus status) {
        return (root, query, builder) -> status != null ? builder.equal(root.get(DeliveryEntity.Fields.status), status) : builder.conjunction();
    }

    public static Specification<DeliveryEntity> totalPriceBetween(BigDecimal totalPriceFrom, BigDecimal totalPriceTo) {
        return (root, query, builder) ->
                totalPriceFrom != null && totalPriceTo != null ?
                        builder.between(root.get(DeliveryEntity.Fields.amount), totalPriceFrom, totalPriceTo) : builder.conjunction();
    }

    public static Specification<DeliveryEntity> deliveredAfter(Timestamp timeFrom) {
        return (root, query, builder) -> timeFrom != null ? builder.greaterThanOrEqualTo(root.get(DeliveryEntity.Fields.createdAt), timeFrom) : builder.conjunction();
    }

    public static Specification<DeliveryEntity> deliveredBefore(Timestamp timeTo) {
        return (root, query, builder) -> timeTo != null ? builder.lessThanOrEqualTo(root.get(DeliveryEntity.Fields.createdAt), timeTo) : builder.conjunction();
    }

}