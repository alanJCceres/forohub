package com.forohub.forohub.mapper;

import com.forohub.forohub.dtos.PerfilDTO;
import com.forohub.forohub.model.Perfil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PerfilMapper {
    PerfilMapper INSTANCE = Mappers.getMapper(PerfilMapper.class);
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "ci", target = "ci")
    Perfil toEntity(PerfilDTO perfilDTO);
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "ci", target = "ci")
    PerfilDTO toDTO(Perfil perfil);
}
