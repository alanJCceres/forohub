package com.forohub.forohub.controller;

import com.forohub.forohub.dtos.CursoDTO;
import com.forohub.forohub.dtos.PerfilDTO;
import com.forohub.forohub.dtos.PerfilResponseDTO;
import com.forohub.forohub.service.implement.PerfilServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class PerfilController {
    private final PerfilServiceImpl perfilService;
    @PostMapping
    @Transactional
    public ResponseEntity<PerfilDTO> savePerfil(@RequestBody @Valid PerfilDTO perfil, @RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader, UriComponentsBuilder uriBuilder){
        PerfilDTO perfilDTO = perfilService.savePerfil(perfil,authHeader);
        URI uri = uriBuilder.path("profile/{id}").buildAndExpand(perfilDTO.getUuid()).toUri();
        return ResponseEntity.created(uri).body(perfilDTO);
    }
    @GetMapping
    public ResponseEntity<PerfilResponseDTO> getPerfil(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader, @RequestParam String id){
        PerfilResponseDTO perfil = perfilService.getPerfil(authHeader,id);
        return ResponseEntity.ok().body(perfil);
    }
}
