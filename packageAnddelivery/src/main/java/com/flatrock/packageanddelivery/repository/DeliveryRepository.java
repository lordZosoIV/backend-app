package com.flatrock.packageanddelivery.repository;

import com.flatrock.packageanddelivery.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long>, JpaSpecificationExecutor<DeliveryEntity> {
}
