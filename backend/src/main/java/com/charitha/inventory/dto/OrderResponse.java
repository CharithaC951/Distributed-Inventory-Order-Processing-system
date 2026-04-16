package com.charitha.inventory.dto;

import java.util.List;

public class OrderResponse {
    private Long id;
    private List<OrderItemResponse> items;
    private String status;

    public OrderResponse(Long id, List<OrderItemResponse> items, String status) {
        this.id = id;
        this.items = items;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }
}