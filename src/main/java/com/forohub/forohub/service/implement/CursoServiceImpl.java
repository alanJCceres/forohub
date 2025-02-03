package com.forohub.forohub.service.implement;

import com.forohub.forohub.dtos.CursoDTO;
import com.forohub.forohub.mapper.CursoMapper;
import com.forohub.forohub.model.Curso;
import com.forohub.forohub.repository.CursoRepository;
import com.forohub.forohub.service.CursoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    public CursoDTO crearCurso(CursoDTO curso){
        System.out.println(curso.getNombre()+" "+curso.getCategoria());
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
        return null;
    }
    public CursoDTO getCurso(UUID id){
        return null;
    }
}
