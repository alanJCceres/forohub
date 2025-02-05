package com.forohub.forohub.service;

import com.forohub.forohub.dtos.LoginRequest;
import com.forohub.forohub.dtos.RegisterRequest;
import com.forohub.forohub.security.TokenResponse;

public interface AuthService {
    TokenResponse register(RegisterRequest registerRequest);
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse refreshToken(String refreshToken);
}
