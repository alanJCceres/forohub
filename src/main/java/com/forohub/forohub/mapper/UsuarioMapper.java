package com.forohub.forohub.mapper;

import com.forohub.forohub.dtos.RegisterRequest;
import com.forohub.forohub.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "contrasenia", target = "contrasenia")
    Usuario toEntity(RegisterRequest usuario);
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "contrasenia", target = "contrasenia")
    RegisterRequest toRegisterRequest(Usuario usuario);
}
