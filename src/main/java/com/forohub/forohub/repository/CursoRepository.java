package com.forohub.forohub.repository;

import com.forohub.forohub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CursoRepository extends JpaRepository<Curso, String> {
}
