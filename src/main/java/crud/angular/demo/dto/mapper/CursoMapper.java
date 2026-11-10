package crud.angular.demo.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import crud.angular.demo.dto.CursoDTO;
import crud.angular.demo.dto.LessonDTO;
import crud.angular.demo.enums.Category;
import crud.angular.demo.model.Curso;

@Component 
public class CursoMapper {
    
    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }
        List<LessonDTO> lessons =  curso.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), 
                lesson.getYoutubeUrl()))
                .collect(Collectors.toList());
        return new CursoDTO(curso.getId(), curso.getName(), curso.getCategory().getValue(),
                lessons);
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
        curso.setCategory(convertCategoryValue(cursoDTO.category()));
        return curso;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Front-end" -> Category.FRONT_END;
            case "Back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };
    }
}
