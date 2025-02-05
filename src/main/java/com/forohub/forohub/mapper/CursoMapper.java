package com.forohub.forohub.mapper;

import com.forohub.forohub.dtos.CursoDTO;
import com.forohub.forohub.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CursoMapper {
    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);
   @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "categoria", target = "categoria")
    Curso toEntity(CursoDTO cursoDTO);
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "categoria", target = "categoria")
    CursoDTO toDTO(Curso curso);
}
