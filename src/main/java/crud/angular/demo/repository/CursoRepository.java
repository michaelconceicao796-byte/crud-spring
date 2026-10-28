package crud.angular.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import crud.angular.demo.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
