package com.ecommerce.order.tracking.system.repository;

import com.ecommerce.order.tracking.system.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}
