package com.forohub.forohub.repository;

import com.forohub.forohub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByCorreo(String email);
    Optional<Usuario> findByUuid(String id);
}
