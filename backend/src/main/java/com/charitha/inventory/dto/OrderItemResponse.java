package com.charitha.inventory.dto;

public class OrderItemResponse {
    private Long productId;
    private int quantity;

    public OrderItemResponse(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
