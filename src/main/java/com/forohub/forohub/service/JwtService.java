package com.forohub.forohub.service;

import com.forohub.forohub.model.Usuario;

public interface JwtService {
    String generateToken(final Usuario usuario);
    String generateRefreshToken(final Usuario usuario);
}
