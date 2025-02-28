package com.forohub.forohub.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginacionDTO<T> {
    private List<T> contenido;
    private int paginaActual;
    private int totalPaginas;
    private long totalElementos;

    public PaginacionDTO(Page<T> pagina) {
        this.contenido = pagina.getContent();
        this.paginaActual = pagina.getNumber();
        this.totalPaginas = pagina.getTotalPages();
        this.totalElementos = pagina.getTotalElements();
    }

    // Getters y Setters
}

