package crud.angular.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import crud.angular.demo.enums.Category;
import crud.angular.demo.model.Curso;
import crud.angular.demo.model.Lesson;
import crud.angular.demo.repository.CursoRepository;

@SpringBootApplication
public class CrudSpringApplication {

    private final CursoRepository cursoRepository;

    CrudSpringApplication(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(CursoRepository repository) {
        return args -> {
            // Insere um exemplo apenas quando o banco estiver vazio.
            // Não apaga os cursos cadastrados quando a aplicação reinicia.
            if (repository.count() == 0) {
                Curso c = new Curso();
                c.setName("Angular com Spring");
                c.setCategory(Category.BACK_END);

                Lesson l = new Lesson();
                l.setName("Introdução");
                l.setYoutubeUrl("https://www.youtube.com/watch?v=Nb4uxLxdvxo&list=PLGxZ4Rq3BOBpwaVgAPxTxhdX_TfSVlTcY&index=45&t=185s");
                l.setCurso(c);
                c.getLessons().add(l);

                cursoRepository.save(c);
            }
        };
    }
}
