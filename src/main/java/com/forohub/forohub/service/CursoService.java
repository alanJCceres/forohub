package com.forohub.forohub.service;

import com.forohub.forohub.dtos.CursoDTO;

import java.util.List;
import java.util.UUID;

public interface CursoService {
    public CursoDTO crearCurso(CursoDTO curso);
    public CursoDTO actualizarCurso(CursoDTO curso);
    public CursoDTO eliminarCurso(CursoDTO curso);
    public List<CursoDTO> listarCursos();
    public CursoDTO getCurso(UUID id);
}
