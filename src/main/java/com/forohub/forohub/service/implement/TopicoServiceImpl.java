package com.forohub.forohub.service.implement;

import com.forohub.forohub.dtos.TopicoDTO;
import com.forohub.forohub.mapper.TopicoMapper;
import com.forohub.forohub.model.Topico;
import com.forohub.forohub.model.Usuario;
import com.forohub.forohub.repository.TopicoRepository;
import com.forohub.forohub.repository.UsuarioRepository;
import com.forohub.forohub.service.JwtService;
import com.forohub.forohub.service.TopicoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicoServiceImpl implements TopicoService {
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TopicoMapper topicoMapper;
    private final JwtServiceImpl jwtService;

    @Override
    public TopicoDTO saveTopico(TopicoDTO topico,String token) {
        final String token2 = token.substring(7);
        if(!token.startsWith("Bearer ") || token2.length() < 20 || token2.split("\\.").length != 3){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Bearer token");
        }
        jwtService.verificarToken(token);
        Topico nuevo = topicoMapper.toEntity(topico);
        final String id = jwtService.extractIdFromToken(token2);
        Optional<Usuario> getUsuario = usuarioRepository.findByUuid(id);
        if(getUsuario.isPresent()){
            nuevo.setUsuario(getUsuario.get());
            return topicoMapper.toDTO(topicoRepository.save(nuevo));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");
        }

    }
}
