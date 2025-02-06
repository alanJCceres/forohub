package com.forohub.forohub.security;

import com.forohub.forohub.model.Usuario;
import com.forohub.forohub.repository.UsuarioRepository;
import com.forohub.forohub.service.implement.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtServiceImpl jwtService;
    private final UserDetailsService userDetailsService;
    private final UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if(request.getServletPath().equals("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        final String userEmail = jwtService.extractUserName(token);
        if(userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        final Optional<Usuario> usuario= usuarioRepository.findByCorreo(userDetails.getUsername());
        if(usuario.isEmpty()){
            filterChain.doFilter(request, response);
            return;
        }

        final boolean isTokenValid = jwtService.isTokenValid(token, usuario.get());
        if(!isTokenValid){
            return;
        }

        final var authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
