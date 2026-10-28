package crud.angular.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import crud.angular.demo.model.Curso;
import crud.angular.demo.repository.CursoRepository;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> list() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso create(Curso curso) {
        curso.setId(null);
        return cursoRepository.save(curso);
    }

    public Optional<Curso> update(Long id, Curso curso) {
        return cursoRepository.findById(id).map(found -> {
            found.setName(curso.getName());
            found.setCategory(curso.getCategory());
            return cursoRepository.save(found);
        });
    }

    public boolean delete(Long id) {
        if (!cursoRepository.existsById(id)) {
            return false;
        }
        cursoRepository.deleteById(id);
        return true;
    }
}
