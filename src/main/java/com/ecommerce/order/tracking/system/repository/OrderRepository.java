package com.ecommerce.order.tracking.system.repository;

import com.ecommerce.order.tracking.system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
