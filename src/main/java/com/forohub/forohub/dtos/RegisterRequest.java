package com.forohub.forohub.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest{
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 5, max = 50,message = "El nombre debe tener minimo 5 y maximo 50 caracteres")
    private String nombre;
    @NotBlank(message = "El correo es obligatorio")
    @Size(min = 10, max = 100,message = "El correo debe tener minimo 20 y maximo 100 caracteres")
    @Email
    private String correo;
    @NotBlank(message = "La contraseña es obligatorio")
    @Size(min = 8, max = 20,message = "La contraseña debe tener minimo 8 y maximo 20 caracteres")
    private String contrasenia;
}
