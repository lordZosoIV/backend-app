package com.flatrcok.order.repository;

import com.flatrcok.order.entity.OrderEntity;
import com.flatrcok.order.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
