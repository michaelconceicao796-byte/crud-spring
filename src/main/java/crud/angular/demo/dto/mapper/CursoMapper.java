package crud.angular.demo.dto.mapper;

import org.springframework.stereotype.Component;

import crud.angular.demo.dto.CursoDTO;
import crud.angular.demo.enums.Category;
import crud.angular.demo.model.Curso;

@Component 
public class CursoMapper {
    
    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }
        return new CursoDTO(curso.getId(), curso.getName(), "Front-end");
    }

    public Curso toEntity(CursoDTO cursoDTO) {

        if (cursoDTO == null) {
            return null;
        }

        Curso curso = new Curso();
        if (cursoDTO.id() != null) {
            curso.setId(cursoDTO.id());
        }
        curso.setName(cursoDTO.name());
        curso.setCategory(Category.FRONT_END);
        curso.setStatus("Ativo");
        return curso;
    }
}
