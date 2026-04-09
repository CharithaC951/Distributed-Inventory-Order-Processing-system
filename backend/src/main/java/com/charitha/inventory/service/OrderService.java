package com.charitha.inventory.service;

import com.charitha.inventory.dto.CreateOrderItemRequest;
import com.charitha.inventory.dto.CreateOrderRequest;
import com.charitha.inventory.entity.Order;
import com.charitha.inventory.entity.OrderItem;
import com.charitha.inventory.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Order createOrder(CreateOrderRequest request) {
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

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    
    public Order getOrderById(long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }
}
