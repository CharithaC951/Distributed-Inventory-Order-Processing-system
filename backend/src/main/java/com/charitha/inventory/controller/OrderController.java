package com.charitha.inventory.controller;

import com.charitha.inventory.entity.Order;
import com.charitha.inventory.entity.OrderItem;
import com.charitha.inventory.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody List<OrderItem> items) {
        return orderService.createOrder(items);
    }
}