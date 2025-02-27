package com.forohub.forohub.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RespuestaDTOPost {
    private String uuid;
    private String mensaje;
    private String topico;
    private String usuario;
}
