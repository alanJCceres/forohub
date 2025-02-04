package com.forohub.forohub.controller;

import com.forohub.forohub.dtos.CursoDTO;
import com.forohub.forohub.service.implement.CursoServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
}
