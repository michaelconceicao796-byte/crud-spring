package crud.angular.demo.dto;

import java.util.List;

public record CursoPageDTO(List<CursoDTO> cursos, long totalElement, int totalPages) {
}
