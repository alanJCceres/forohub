package com.forohub.forohub.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no es un correo valido")
        @Size(min = 10, max = 100,message = "El correo debe tener minimo 20 y maximo 100 caracteres")
        String correo,
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, max = 20,message = "La contraseña debe tener minimo 8 y maximo 20 caracteres")
        String contrasenia
) {
}
