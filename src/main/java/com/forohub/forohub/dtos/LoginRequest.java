package com.forohub.forohub.dtos;

public record LoginRequest(
        String correo,
        String contrasenia
) {
}
