package com.charitha.inventory.controller;

import com.charitha.inventory.dto.AuthRequest;
import com.charitha.inventory.dto.AuthResponse;
import com.charitha.inventory.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}