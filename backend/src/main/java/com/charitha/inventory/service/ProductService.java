package com.charitha.inventory.service;

import com.charitha.inventory.entity.Product;
import com.charitha.inventory.exception.ProductNotFoundException;
import com.charitha.inventory.repository.ProductRepository;
import com.charitha.inventory.security.TenantContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        Long tenantId = TenantContext.getTenantId();

        return productRepository.findByIdAndTenantId(id, tenantId)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getAllProducts() {
        Long tenantId = TenantContext.getTenantId();
        return productRepository.findByTenantId(tenantId);
    }
}