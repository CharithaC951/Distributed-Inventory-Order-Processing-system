package com.charitha.inventory.service;

import com.charitha.inventory.entity.Product;
import com.charitha.inventory.exception.InsufficientStockException;
import com.charitha.inventory.exception.InvalidQuantityException;
import com.charitha.inventory.exception.ProductNotFoundException;
import com.charitha.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void shouldDecreaseStockWhenEnoughInventoryExists() {
        Product product = new Product(1L, "Laptop", 10);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        inventoryService.decreaseStock(1L, 3);

        assertEquals(7, product.getStockQuantity());
        verify(productRepository).save(product);
    }

    //If requested quantity is greater than available stock, throw an exception.
    @Test
    void shouldThrowExceptionWhenStockIsInsufficient(){
        Product product = new Product(1L, "Laptop", 3);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(InsufficientStockException.class, () ->
                inventoryService.decreaseStock(1L, 5)
        );

        verify(productRepository, never()).save(product);
    }

    @Test
    void shouldThrowExceptionWhenQuantityIsInvalid() {
        assertThrows(InvalidQuantityException.class, () ->
                inventoryService.decreaseStock(1L, 0)
        );
        verify(productRepository, never()).findById(1L);
        verify(productRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () ->
                inventoryService.decreaseStock(1L, 2)
        );

        verify(productRepository, never()).save(any());
    }

    @Test
    void shouldIncreaseStockWhenValidQuantityIsProvided(){
        Product product = new Product(1L, "Laptop", 10);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        inventoryService.increaseStock(1L,5);

        assertEquals(15, product.getStockQuantity());
        verify(productRepository).save(product);
    }

    @Test
    void shouldThrowExceptionWhenIncreaseQuantityIsInvalid() {
        assertThrows(InvalidQuantityException.class, () ->
                inventoryService.increaseStock(1L, 0)
        );

        verify(productRepository, never()).findById(1L);
        verify(productRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenIncreasingStockForNonExistingProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () ->
                inventoryService.increaseStock(1L, 5)
        );

        verify(productRepository, never()).save(any());
    }
}