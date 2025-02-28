package com.forohub.forohub.repository;

import com.forohub.forohub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, String> {
    Optional<Topico> findByUuid(String id);
    Page<Topico> findByUsuarioUuid(String usuario, Pageable pageable);
}
