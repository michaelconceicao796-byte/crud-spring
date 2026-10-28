package crud.angular.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import crud.angular.demo.model.Curso;
import crud.angular.demo.repository.CursoRepository;

@SpringBootApplication
public class CrudSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(CursoRepository repository) {
        return args -> {
            // Insere um exemplo apenas quando o banco estiver vazio.
            // Não apaga os cursos cadastrados quando a aplicação reinicia.
            if (repository.count() == 0) {
                Curso curso = new Curso();
                curso.setName("Angular com Spring");
                curso.setCategory("Front-end");
                repository.save(curso);
            }
        };
    }
}
