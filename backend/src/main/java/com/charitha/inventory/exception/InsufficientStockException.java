package com.charitha.inventory.exception;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(Long productId, int requestedQuantity, int availableQuantity) {
        super("Insufficient stock for product id: " + productId
                + ". Requested: " + requestedQuantity
                + ", Available: " + availableQuantity);
    }
}