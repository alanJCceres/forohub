package com.forohub.forohub.mapper;

import com.forohub.forohub.dtos.PerfilDTO;
import com.forohub.forohub.dtos.PerfilResponseDTO;
import com.forohub.forohub.model.Perfil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PerfilMapper {
    Perfil toEntity(PerfilDTO perfilDTO);
    PerfilDTO toDTO(Perfil perfil);
    @Mapping(source="usuario.nombre",target="nombreUsuario")
    @Mapping(source="usuario.correo",target="correo")
    PerfilResponseDTO toResponseDTO(Perfil perfil);
}
