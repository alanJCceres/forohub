package com.forohub.forohub.repository;

import com.forohub.forohub.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RespuestaRepository extends JpaRepository<Respuesta, String> {
    Optional<Respuesta> findByUuid(String uuid);
}
