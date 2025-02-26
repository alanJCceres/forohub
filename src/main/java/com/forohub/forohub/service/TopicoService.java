package com.forohub.forohub.service;

import com.forohub.forohub.dtos.TopicoDTO;

public interface TopicoService {
    TopicoDTO saveTopico(TopicoDTO topico,String token);
}
