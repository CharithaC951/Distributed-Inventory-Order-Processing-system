package com.charitha.inventory.service;

import com.charitha.inventory.entity.Product;
import com.charitha.inventory.exception.InsufficientStockException;
import com.charitha.inventory.exception.InvalidQuantityException;
import com.charitha.inventory.exception.ProductNotFoundException;
import com.charitha.inventory.repository.ProductRepository;
import com.charitha.inventory.security.TenantContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Retryable(
            retryFor = {RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 500)
    )
    @Transactional
    public void decreaseStock(Long productId, int quantity) {
        // we will implement this after writing the test
        if (quantity <= 0) {
            throw new InvalidQuantityException(quantity);
        }
        Product product = productRepository.findByIdAndTenantId(productId, TenantContext.getTenantId())
                .orElseThrow(() -> new ProductNotFoundException(productId));

        if (quantity > product.getStockQuantity()) {
            throw new InsufficientStockException(productId, quantity, product.getStockQuantity());
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);

        productRepository.save(product);
    }

    @Retryable(
            retryFor = {RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 500)
    )
    @Transactional
    public void increaseStock(long productId, int quantity) {
        if(quantity <=0){
            throw new InvalidQuantityException(quantity);
        }
        Product product = productRepository.findByIdAndTenantId(productId, TenantContext.getTenantId())
                .orElseThrow(() -> new ProductNotFoundException(productId));

        product.setStockQuantity(product.getStockQuantity()+quantity);

        productRepository.save(product);
    }
}
