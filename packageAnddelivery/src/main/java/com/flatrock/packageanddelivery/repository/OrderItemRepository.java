package com.flatrock.packageanddelivery.repository;

import com.flatrock.packageanddelivery.entity.OrderEntity;
import com.flatrock.packageanddelivery.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
