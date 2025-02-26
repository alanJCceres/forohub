package com.forohub.forohub.mapper;

import com.forohub.forohub.dtos.TopicoDTO;
import com.forohub.forohub.model.Topico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicoMapper {
    TopicoMapper INSTANCE = Mappers.getMapper(TopicoMapper.class);
    @Mapping(source = "titulo", target = "titulo")
    @Mapping(source = "mensaje", target = "mensaje")
    @Mapping(source = "status", target = "status")
    Topico toEntity(TopicoDTO topicoDTO);
    @Mapping(source = "titulo", target = "titulo")
    @Mapping(source = "mensaje", target = "mensaje")
    @Mapping(source = "status", target = "status")
    TopicoDTO toDTO(Topico topico);
}
