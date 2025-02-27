package com.forohub.forohub.mapper;

import com.forohub.forohub.dtos.RegisterRequest;
import com.forohub.forohub.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(RegisterRequest usuario);
    RegisterRequest toRegisterRequest(Usuario usuario);
}
