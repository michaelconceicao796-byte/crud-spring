package crud.angular.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

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

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> list() {
        return cursoRepository.findAll();
    }

    public Curso findById(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new RecordNotFoundExcepition(id));
    }

    public Curso create(@Valid Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso update(@NotNull @Positive Long id, Curso curso) {
        return cursoRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(curso.getName());
            recordFound.setCategory(curso.getCategory());
            return cursoRepository.save(recordFound);
        }).orElseThrow(() -> new RecordNotFoundExcepition(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) {

        cursoRepository.delete(cursoRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundExcepition(id)));
    }
}
