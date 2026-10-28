package crud.angular.demo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import crud.angular.demo.model.Curso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> list() {
        return cursoService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable @Positive Long id) {
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso create(@RequestBody @Valid Curso curso) {
        return cursoService.create(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(
            @PathVariable @Positive Long id,
            @RequestBody @Valid Curso curso) {
        return cursoService.update(id, curso)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        return cursoService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
