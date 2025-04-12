package com.forohub.forohub.service.implement;

import com.forohub.forohub.dtos.CursoDTO;
import com.forohub.forohub.mapper.CursoMapper;
import com.forohub.forohub.model.Curso;
import com.forohub.forohub.repository.CursoRepository;
import com.forohub.forohub.service.CursoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    public CursoDTO crearCurso(CursoDTO curso){
        Curso nuevo = cursoRepository.save(cursoMapper.toEntity(curso));
        return cursoMapper.toDTO(nuevo);
    }
    public CursoDTO actualizarCurso(CursoDTO curso){
        return null;
    }
    public CursoDTO eliminarCurso(CursoDTO curso){
        return null;
    }
    public List<CursoDTO> listarCursos(){
        List<Curso> cursos = cursoRepository.findAll();
        List<CursoDTO> cursoDTOs = new ArrayList<>();
        for (Curso curso : cursos) {
            cursoDTOs.add(cursoMapper.toDTO(curso));
        }
        return cursoDTOs;
    }
}
