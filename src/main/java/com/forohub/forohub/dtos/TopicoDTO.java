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
public class TopicoDTO {
    private String uuid;
    @NotBlank(message = "El titulo no puede ser vacio")
    @Size(min = 2, max = 50,message = "El titulo debe tener minimo 2 y maximo 50 caracteres")
    private String titulo;
    @NotBlank(message = "El mensaje no puede ser vacio")
    @Size(min = 2, max = 50,message = "El mensaje debe tener minimo 2 y maximo 50 caracteres")
    private String mensaje;
    @NotBlank(message = "El estado no puede ser vacio")
    @Size(min = 2, max = 50,message = "El estado debe tener minimo 2 y maximo 50 caracteres")
    private String status;
}
