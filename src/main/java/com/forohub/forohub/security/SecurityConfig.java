package com.forohub.forohub.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/v3/api-docs/**","/swagger-ui.html","/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> //METODO PARA LOGOUT
                        logout.logoutUrl("/auth/logout")
                                .addLogoutHandler((request,response,authentication)->{
                                    final var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                                    logout(authHeader);
                                })
                                .logoutSuccessHandler((request,response,authentication)->
                                        SecurityContextHolder.clearContext())
                );
        return http.build();
    }
    private void logout(final String token){
        if(token == null || !token.startsWith("Bearer ")){
            throw new IllegalArgumentException("token invalido");
        }
        final String jwtToken = token.substring(7);
    }
}

