package com.forohub.forohub.service.implement;

import com.forohub.forohub.dtos.LoginRequest;
import com.forohub.forohub.dtos.RegisterRequest;
import com.forohub.forohub.mapper.UsuarioMapper;
import com.forohub.forohub.model.Usuario;
import com.forohub.forohub.repository.UsuarioRepository;
import com.forohub.forohub.security.TokenResponse;
import com.forohub.forohub.service.AuthService;
import com.forohub.forohub.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenResponse register(RegisterRequest registerRequest){
        Usuario nuevo = usuarioMapper.toEntity(registerRequest);
        nuevo.setContrasenia(passwordEncoder.encode(nuevo.getContrasenia()));
        Usuario savedUser = usuarioRepository.save(nuevo);
        String jwtToken = jwtService.generateToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);
        return new TokenResponse(jwtToken, refreshToken);
    }
    public TokenResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.correo(),
                    loginRequest.contrasenia()
                )
        );
        Usuario usuario = usuarioRepository.findByCorreo(loginRequest.correo())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(usuario);
        String refreshToken = jwtService.generateRefreshToken(usuario);
        return new TokenResponse(jwtToken, refreshToken);
    }
    public TokenResponse refreshToken(final String refreshToken){
        if(refreshToken == null || !refreshToken.startsWith("Bearer ")){
            throw new IllegalArgumentException("Invalid Bearer token");
        }

        final String refreshToken2 = refreshToken.substring(7);
        final String userEmail = jwtService.extractUserName(refreshToken2);
        if(userEmail == null || userEmail.isEmpty()){
            throw new IllegalArgumentException("Invalid Refresh Token");
        }

        final Usuario usuario = usuarioRepository.findByCorreo(userEmail)
                .orElseThrow(()-> new UsernameNotFoundException(userEmail));
        if(!jwtService.isTokenValid(refreshToken2,usuario)){
            throw new IllegalArgumentException("Invalid Refresh Token");
        }

        final String accessToken = jwtService.generateToken(usuario);
        return new TokenResponse(accessToken, refreshToken2);
    }
}
