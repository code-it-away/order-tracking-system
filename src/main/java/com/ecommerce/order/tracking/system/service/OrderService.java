package com.ecommerce.order.tracking.system.service;

import com.ecommerce.order.tracking.system.dto.CreateOrderRequest;
import com.ecommerce.order.tracking.system.entity.Order;
import com.ecommerce.order.tracking.system.entity.OrderHistory;
import com.ecommerce.order.tracking.system.repository.OrderRepository;
import com.ecommerce.order.tracking.system.repository.OrderHistoryRepository;
import com.ecommerce.order.tracking.system.contants.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderHistoryRepository orderHistoryRepository) {
        this.orderRepository = orderRepository;
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Transactional
    public Order createOrder(CreateOrderRequest orderRequest){
        Order order = new Order();
        // map DTO -> entity (Order.userId is a String in the entity; DTO has Long userId)
        if (orderRequest != null && orderRequest.getUserId() != null) {
            order.setUserId(String.valueOf(orderRequest.getUserId()));
        }
        order.setStatus(OrderStatus.PLACED);
        LocalDateTime now = LocalDateTime.now();
        order.setCreatedAt(now);
        order.setUpdatedAt(now);
        Order savedEntity = orderRepository.save(order);
        // create and save order history in the same transaction
        OrderHistory history = new OrderHistory();
        history.setOrder(savedEntity);
        history.setStatus(OrderStatus.PLACED);
        history.setCreatedAt(now);
        orderHistoryRepository.save(history);

        return savedEntity;
    }

    /**
     * Retrieve an order by ID.
     *
     * @param orderId the order ID
     * @return Optional containing the Order if found, empty otherwise
     */
    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

}
