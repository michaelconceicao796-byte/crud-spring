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

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(CursoRepository cursoRepository) {
        return args -> {
            cursoRepository.deleteAll();
            
                for (int i=0; i<20; i++) {
                
                    Curso c = new Curso();
                    c.setName("Angular com Spring" + i);
                    c.setCategory(Category.BACK_END);

                    Lesson l = new Lesson();
                    l.setName("Introdução");
                    l.setYoutubeUrl("01234567890");
                    l.setCurso(c);
                    c.getLessons().add(l);

                    Lesson l1 = new Lesson();
                    l1.setName("Angular");
                    l1.setYoutubeUrl("01234567891");
                    l1.setCurso(c);
                    c.getLessons().add(l1);

                    cursoRepository.save(c);
                }
        };
    }
}
