package com.forohub.forohub.service.implement;

import com.forohub.forohub.dtos.PerfilDTO;
import com.forohub.forohub.dtos.PerfilResponseDTO;
import com.forohub.forohub.mapper.PerfilMapper;
import com.forohub.forohub.model.Perfil;
import com.forohub.forohub.model.Usuario;
import com.forohub.forohub.repository.PerfilRepository;
import com.forohub.forohub.repository.UsuarioRepository;
import com.forohub.forohub.service.PerfilService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PerfilServiceImpl implements PerfilService {
    private PerfilRepository perfilRepository;
    private UsuarioRepository usuarioRepository;
    private final JwtServiceImpl jwtService;
    private final PerfilMapper perfilMapper;


    public PerfilResponseDTO getPerfil(String token,String id){
        final String token2 = token.substring(7);
        if(token == null || !token.startsWith("Bearer ") || token2.length() < 20 || token2.split("\\.").length != 3){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Bearer token");
        }
        jwtService.verificarToken(token);
        Optional<Perfil> perfil = perfilRepository.findByUuid(id);
        if(perfil.isPresent()){
            return perfilMapper.toResponseDTO(perfil.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil no encontrado");
        }
    }
    public PerfilDTO savePerfil(PerfilDTO perfil,String token){
        final String token2 = token.substring(7);
        if(token == null || !token.startsWith("Bearer ") || token2.length() < 20 || token2.split("\\.").length != 3){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Bearer token");
        }
        jwtService.verificarToken(token);
        Perfil nuevo= perfilMapper.toEntity(perfil);
        final String id = jwtService.extractIdFromToken(token2);
        Optional<Usuario> getUsuario = usuarioRepository.findByUuid(id);
        if(getUsuario.isPresent()){
            if (perfilRepository.existsByUsuario(getUsuario.get())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya tiene un perfil registrado");
            }
            nuevo.setUsuario(getUsuario.get());
            return perfilMapper.toDTO(perfilRepository.save(nuevo));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");
        }
    }
}
