package com.forohub.forohub.controller;

import com.forohub.forohub.dtos.CursoDTO;
import com.forohub.forohub.service.implement.CursoServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/curso")
public class CursoController {
    private final CursoServiceImpl cursoService;
    @PostMapping
    @Transactional
    public ResponseEntity<CursoDTO> crearCurso(@Valid @RequestBody CursoDTO curso, UriComponentsBuilder uriBuilder) {
        CursoDTO cursoDTO = cursoService.crearCurso(curso);
        URI uri = uriBuilder.path("curso/{id}").buildAndExpand(cursoDTO.getUuid()).toUri();
        return ResponseEntity.created(uri).body(cursoDTO);
    }
    @GetMapping
    public ResponseEntity<List<CursoDTO>> listarCursos() {
        List<CursoDTO> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }
}
