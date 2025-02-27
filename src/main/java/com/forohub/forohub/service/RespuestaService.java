package com.forohub.forohub.service;

import com.forohub.forohub.dtos.RespuestaDTOPost;
import com.forohub.forohub.model.Respuesta;

public interface RespuestaService {
    RespuestaDTOPost saveRespuesta(RespuestaDTOPost respuesta,String token,String topicoId);
}
