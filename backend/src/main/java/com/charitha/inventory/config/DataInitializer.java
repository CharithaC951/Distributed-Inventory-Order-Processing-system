package com.charitha.inventory.config;

import com.charitha.inventory.entity.Product;
import com.charitha.inventory.entity.Role;
import com.charitha.inventory.entity.User;
import com.charitha.inventory.repository.ProductRepository;
import com.charitha.inventory.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserRepository userRepository,
                               ProductRepository productRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("Admin@123"));
                admin.setRole(Role.ADMIN);
                admin.setTenantId(1L);
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("tenant2admin").isEmpty()) {
                User admin2 = new User();
                admin2.setUsername("tenant2admin");
                admin2.setPassword(passwordEncoder.encode("Admin@123"));
                admin2.setRole(Role.ADMIN);
                admin2.setTenantId(2L);
                userRepository.save(admin2);
            }

            if (productRepository.findByTenantId(1L).isEmpty()) {
                Product p1 = new Product();
                p1.setName("Laptop");
                p1.setStockQuantity(20);
                p1.setTenantId(1L);
                productRepository.save(p1);

                Product p2 = new Product();
                p2.setName("Mouse");
                p2.setStockQuantity(50);
                p2.setTenantId(1L);
                productRepository.save(p2);
            }

            if (productRepository.findByTenantId(2L).isEmpty()) {
                Product p3 = new Product();
                p3.setName("Printer");
                p3.setStockQuantity(10);
                p3.setTenantId(2L);
                productRepository.save(p3);

                Product p4 = new Product();
                p4.setName("Scanner");
                p4.setStockQuantity(8);
                p4.setTenantId(2L);
                productRepository.save(p4);
            }
        };
    }
}