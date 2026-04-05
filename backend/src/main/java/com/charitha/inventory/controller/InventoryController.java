package com.charitha.inventory.controller;

import com.charitha.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/{productId}/increase")
    public ResponseEntity<String> increaseStock(@PathVariable Long productId,
                                                @RequestParam int quantity) {
        inventoryService.increaseStock(productId, quantity);
        return ResponseEntity.ok("Stock increased successfully");
    }

    @PostMapping("/{productId}/decrease")
    public ResponseEntity<String> decreaseStock(@PathVariable Long productId,
                                                @RequestParam int quantity) {
        inventoryService.decreaseStock(productId, quantity);
        return ResponseEntity.ok("Stock decreased successfully");
    }
}