package com.ecommerce.order.tracking.system.controller;

import com.ecommerce.order.tracking.system.dto.CreateOrderRequest;
import com.ecommerce.order.tracking.system.dto.OrderResponse;
import com.ecommerce.order.tracking.system.entity.Order;
import com.ecommerce.order.tracking.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Create a new order.
     *
     * Request body should contain only userId.
     * Server generates: orderId, status (PLACED), createdAt, updatedAt.
     *
     * @param request CreateOrderRequest with userId
     * @return OrderResponse with orderId and status
     */
    @PostMapping("/create-order")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        // Call service to create order and save order history
        Order savedOrder = orderService.createOrder(request);

        // Map Order entity to OrderResponse DTO
        OrderResponse response = new OrderResponse();
        response.setId(savedOrder.getId());
        response.setStatus(savedOrder.getStatus().toString());

        // Return 201 Created with response
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retrieve an order by ID.
     *
     * @param orderId the order ID
     * @return OrderResponse with orderId and status, or 404 if not found
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {
        Optional<Order> order = orderService.getOrder(orderId);

        if (order.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Map Order entity to OrderResponse DTO
        Order foundOrder = order.get();
        OrderResponse response = new OrderResponse();
        response.setId(foundOrder.getId());
        response.setStatus(foundOrder.getStatus().toString());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
