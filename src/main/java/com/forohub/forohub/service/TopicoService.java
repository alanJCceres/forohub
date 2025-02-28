package com.forohub.forohub.service;

import com.forohub.forohub.dtos.TopicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicoService {
    TopicoDTO saveTopico(TopicoDTO topico,String token);
    Page<TopicoDTO> getTopicos(Pageable pageable,String token);
}
