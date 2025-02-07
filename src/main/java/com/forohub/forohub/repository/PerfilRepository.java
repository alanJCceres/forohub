package com.forohub.forohub.repository;

import com.forohub.forohub.model.Perfil;
import com.forohub.forohub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil, String> {
    Optional<Perfil> findByUuid(String uuid);
    boolean existsByUsuario(Usuario usuario);
}
