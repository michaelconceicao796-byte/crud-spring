package crud.angular.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import crud.angular.demo.dto.CursoDTO;
import crud.angular.demo.dto.mapper.CursoMapper;
import crud.angular.demo.enums.Category;
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

    public CursoDTO findById(@NotNull @Positive Long id) {
        return cursoRepository.findById(id).map(cursoMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundExcepition(id));
    }

    public CursoDTO create(@Valid @NotNull CursoDTO curso) {
        return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(curso)));
    }

    public CursoDTO update(@NotNull @Positive Long id, @Valid @NotNull CursoDTO cursoDTO) {
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    Curso curso = cursoMapper.toEntity(cursoDTO);
                    recordFound.setName(cursoDTO.name());
                    recordFound.setCategory(cursoMapper.convertCategoryValue(cursoDTO.category()));
                    // recordFound.setLessons(curso.getLessons());
                    recordFound.getLessons().clear();
                    curso.getLessons().forEach(recordFound.getLessons()::add);
                    return cursoMapper.toDTO(cursoRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundExcepition(id));
    }

    public void delete(@NotNull @Positive Long id) {
    Curso curso = cursoRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundExcepition(id));

    cursoRepository.delete(curso);
}
}
