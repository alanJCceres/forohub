package com.forohub.forohub.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PerfilResponseDTO {
    private String uuid;
    private String nombre;
    private String ci;
    private String nombreUsuario;
    private String correo;
}
