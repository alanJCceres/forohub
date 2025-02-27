package com.forohub.forohub.mapper;

import com.forohub.forohub.dtos.TopicoDTO;
import com.forohub.forohub.model.Topico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicoMapper {
    Topico toEntity(TopicoDTO topicoDTO);
    TopicoDTO toDTO(Topico topico);
}
