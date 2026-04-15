package com.charitha.inventory.service;

import com.charitha.inventory.dto.CreateOrderItemRequest;
import com.charitha.inventory.dto.CreateOrderRequest;
import com.charitha.inventory.dto.OrderItemResponse;
import com.charitha.inventory.dto.OrderResponse;
import com.charitha.inventory.entity.Order;
import com.charitha.inventory.entity.OrderItem;
import com.charitha.inventory.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.charitha.inventory.exception.OrderNotFoundException;
import java.util.List;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;

    public OrderService(OrderRepository orderRepository,
                        InventoryService inventoryService) {
        this.orderRepository = orderRepository;
        this.inventoryService = inventoryService;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = new Order();

        for (CreateOrderItemRequest itemRequest : request.getItems()) {
            inventoryService.decreaseStock(
                    itemRequest.getProductId(),
                    itemRequest.getQuantity()
            );

            OrderItem orderItem = new OrderItem(
                    itemRequest.getProductId(),
                    itemRequest.getQuantity()
            );

            order.addItem(orderItem);
        }

        Order savedOrder = orderRepository.save(order);
        return mapToResponse(savedOrder);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));

        return mapToResponse(order);
    }

    private OrderResponse mapToResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getItems().stream()
                        .map(item -> new OrderItemResponse(item.getProductId(), item.getQuantity()))
                        .toList()
        );
    }
}
