package crud.angular.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import crud.angular.demo.dto.CursoDTO;
import crud.angular.demo.dto.mapper.CursoMapper;
import crud.angular.demo.excepition.RecordNotFoundExcepition;
import crud.angular.demo.model.Curso;
import crud.angular.demo.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated 
@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoDTO> list() {
        return cursoRepository.findAll()
        .stream()
        .map(cursoMapper::toDTO)
        .collect(Collectors.toList());
    }


    public CursoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id).map(cursoMapper::toDTO)
        .orElseThrow(() -> new RecordNotFoundExcepition(id));
    }

    public CursoDTO create(@Valid @NotNull CursoDTO curso) {
        return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(curso)));
    }

    public CursoDTO update(@NotNull @Positive Long id, @Valid @NotNull CursoDTO curso) {
        return cursoRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(curso.name());
                recordFound.setCategory(curso.category());
                return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(curso)));
        }).orElseThrow(() -> new RecordNotFoundExcepition(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        cursoRepository.delete(cursoRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundExcepition(id)));
    }
}
