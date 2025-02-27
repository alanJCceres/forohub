package com.forohub.forohub.controller;

import com.forohub.forohub.dtos.RespuestaDTOPost;
import com.forohub.forohub.service.implement.RespuestaServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuesta")
@AllArgsConstructor
public class RespuestaController {
    private final RespuestaServiceImpl respuestaService;
    @PostMapping()
    @Transactional
    public ResponseEntity<RespuestaDTOPost> saveRespuesta(@RequestBody @Valid RespuestaDTOPost respuestaDTOPost,
                                                          @RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader,@RequestParam String topico,
                                                            UriComponentsBuilder uriBuilder) {
        RespuestaDTOPost respueta = respuestaService.saveRespuesta(respuestaDTOPost, authHeader, topico);
        URI uri = uriBuilder.path("respuesta/{id}").buildAndExpand(respueta.getUuid()).toUri();
        return ResponseEntity.created(uri).body(respueta);
    }
}
