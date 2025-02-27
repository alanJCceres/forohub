package com.forohub.forohub.mapper;

import com.forohub.forohub.dtos.RespuestaDTOPost;
import com.forohub.forohub.model.Respuesta;
import com.forohub.forohub.model.Topico;
import com.forohub.forohub.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RespuestaMapper {
    @Mapping(source = "mensaje", target = "mensaje")
    @Mapping(source = "topico", target = "topico", qualifiedByName = "stringToTopico")
    @Mapping(source = "usuario", target = "usuario", qualifiedByName = "stringToUsuario")
    Respuesta toEntity(RespuestaDTOPost respuestaDTOPost);
    @Mapping(source = "mensaje", target = "mensaje")
    @Mapping(source = "topico.uuid", target = "topico")
    @Mapping(source = "usuario.uuid", target = "usuario")
    RespuestaDTOPost toDto(Respuesta respuesta);

    // Métodos de conversión personalizados
    @Named("stringToTopico")
    default Topico stringToTopico(String topicoId) {
        if (topicoId == null) {
            return null;
        }
        return new Topico(topicoId); // Suponiendo que Topico tiene un constructor que recibe un String
    }
    @Named("stringToUsuario")
    default Usuario stringToUsuario(String usuarioId) {
        if (usuarioId == null) {
            return null;
        }
        return new Usuario(usuarioId); // Suponiendo que Usuario tiene un constructor que recibe un String
    }
}
