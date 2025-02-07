package com.forohub.forohub.dtos;

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
public class PerfilDTO {
    private String uuid;
    @NotBlank(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 50,message = "El nombre debe tener minimo 2 y maximo 50 caracteres")
    private String nombre;
    @NotBlank(message = "El ci no puede ser nulo")
    @Size(min = 7, max = 8,message = "El ci debe ser minimo 7 y maximo 8 caracteres")
    private String ci;
}
