package com.forohub.forohub.service;

import com.forohub.forohub.dtos.PerfilDTO;

public interface PerfilService {
    PerfilDTO getPerfil();
    PerfilDTO savePerfil(PerfilDTO perfil,String token);
}
