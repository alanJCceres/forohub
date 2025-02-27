package com.forohub.forohub.mapper;

import com.forohub.forohub.dtos.CursoDTO;
import com.forohub.forohub.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CursoMapper {
    Curso toEntity(CursoDTO cursoDTO);
    CursoDTO toDTO(Curso curso);
}
