package com.charitha.inventory.dto;

import java.util.List;

public class OrderResponse {
    private Long id;
    private List<OrderItemResponse> items;

    public OrderResponse(Long id, List<OrderItemResponse> items) {
        this.id = id;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }
}