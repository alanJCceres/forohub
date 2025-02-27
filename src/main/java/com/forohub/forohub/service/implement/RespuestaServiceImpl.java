package com.forohub.forohub.service.implement;

import com.forohub.forohub.dtos.RespuestaDTOPost;
import com.forohub.forohub.mapper.RespuestaMapper;
import com.forohub.forohub.model.Respuesta;
import com.forohub.forohub.model.Topico;
import com.forohub.forohub.model.Usuario;
import com.forohub.forohub.repository.RespuestaRepository;
import com.forohub.forohub.repository.TopicoRepository;
import com.forohub.forohub.repository.UsuarioRepository;
import com.forohub.forohub.service.RespuestaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RespuestaServiceImpl implements RespuestaService {
    private final RespuestaRepository respuestaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TopicoRepository topicoRepository;
    private final RespuestaMapper respuestaMapper;
    private final JwtServiceImpl jwtService;

    @Override
    public RespuestaDTOPost saveRespuesta(RespuestaDTOPost respuesta, String token,String topicoId) {
        final String token2 = token.substring(7);
        if(!token.startsWith("Bearer ") || token2.length() < 20 || token2.split("\\.").length != 3){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Bearer token");
        }
        jwtService.verificarToken(token);
        final String id = jwtService.extractIdFromToken(token2);
        Optional<Usuario> getUsuario = usuarioRepository.findByUuid(id);
        Optional<Topico> getTopico = topicoRepository.findByUuid(topicoId);
        if(getUsuario.isPresent()) {
            if(getTopico.isPresent()) {
                Respuesta nuevo = respuestaMapper.toEntity(respuesta);
                nuevo.setUsuario(getUsuario.get());
                nuevo.setTopico(getTopico.get());
                return respuestaMapper.toDto(respuestaRepository.save(nuevo));
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El topico no existe");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");
        }
    }
}
