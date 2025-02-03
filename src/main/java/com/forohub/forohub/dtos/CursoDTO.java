package com.forohub.forohub.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO{
        private String uuid;
        @NotBlank(message = "El nombre no puede ser nulo")
        @Size(min = 2, max = 100)
        private String nombre;
        @NotBlank(message = "La categoria no puede ser nulo")
        @Size(min = 2, max = 100)
        private String categoria;
        private Date createdAt;
        private Date updatedAt;
}
