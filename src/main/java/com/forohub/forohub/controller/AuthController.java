package com.forohub.forohub.controller;

import com.forohub.forohub.dtos.LoginRequest;
import com.forohub.forohub.dtos.RegisterRequest;
import com.forohub.forohub.security.TokenResponse;
import com.forohub.forohub.service.implement.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody @Valid final RegisterRequest request){
        final TokenResponse token = service.register(request);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody @Valid final LoginRequest request){
        final TokenResponse token = service.login(request);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/refresh")
    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader){
        return service.refreshToken(authHeader);
    }
}
