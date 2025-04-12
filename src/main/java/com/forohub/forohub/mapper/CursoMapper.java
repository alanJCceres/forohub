package com.forohub.forohub.mapper;

import com.forohub.forohub.dtos.CursoDTO;
import com.forohub.forohub.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CursoMapper {
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "categoria", target = "categoria")
    Curso toEntity(CursoDTO cursoDTO);
    @Mapping(source = "uuid", target = "uuid")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    CursoDTO toDTO(Curso curso);
}
