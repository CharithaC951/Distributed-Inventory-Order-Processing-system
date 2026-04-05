package com.charitha.inventory.service;

import com.charitha.inventory.entity.Product;
import com.charitha.inventory.exception.ProductNotFoundException;
import com.charitha.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException(productId));
    }
}