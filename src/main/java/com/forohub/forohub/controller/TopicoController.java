package com.forohub.forohub.controller;

import com.forohub.forohub.dtos.PaginacionDTO;
import com.forohub.forohub.dtos.TopicoDTO;
import com.forohub.forohub.service.TopicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topico")
@AllArgsConstructor
public class TopicoController {
    private final TopicoService topicoService;
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> saveTopico(@RequestBody @Valid TopicoDTO topicoDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader, UriComponentsBuilder uriBuilder) {
        TopicoDTO topico = topicoService.saveTopico(topicoDTO, authHeader);
        URI uri = uriBuilder.path("topico/{id}").buildAndExpand(topico.getUuid()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }
    @GetMapping
    public ResponseEntity<PaginacionDTO<TopicoDTO>> getAllTopicos(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader, Pageable pageable) {
        Page<TopicoDTO> topicos = topicoService.getTopicos(pageable,authHeader);
        return ResponseEntity.ok(new PaginacionDTO<>(topicos));
    }
}
