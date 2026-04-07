package com.charitha.inventory.service;

import com.charitha.inventory.entity.Order;
import com.charitha.inventory.entity.OrderItem;
import com.charitha.inventory.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(List<OrderItem> items) {
        Order order = new Order();

        for (OrderItem item : items) {
            order.addItem(item); // important (sets relationship)
        }

        return orderRepository.save(order);
    }
}
