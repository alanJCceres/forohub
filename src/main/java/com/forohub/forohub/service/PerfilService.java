package com.forohub.forohub.service;

import com.forohub.forohub.dtos.PerfilDTO;
import com.forohub.forohub.dtos.PerfilResponseDTO;

public interface PerfilService {
    PerfilResponseDTO getPerfil(String token,String id);
    PerfilDTO savePerfil(PerfilDTO perfil,String token);
}
