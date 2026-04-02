package com.charitha.inventory.exception;

public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException(int requestedQuantity) {
        super(requestedQuantity + " is Invalid, please provide a positive and valid quantity.");
    }
}
