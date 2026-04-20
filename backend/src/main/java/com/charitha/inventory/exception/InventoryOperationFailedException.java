package com.charitha.inventory.exception;

public class InventoryOperationFailedException extends RuntimeException {
    public InventoryOperationFailedException(String message) {
        super(message);
    }
}