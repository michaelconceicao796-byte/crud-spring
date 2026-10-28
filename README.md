# CRUD Spring Boot

Backend REST para o projeto Angular. Usa Java 17, Spring Boot 3.5.5 e H2 em memória.

## Executar no Windows

Abra esta pasta no VS Code. Confirme que um JDK 17 está instalado e que `JAVA_HOME` aponta para a pasta do JDK.

```powershell
.\mvnw.cmd spring-boot:run
```

API: `http://localhost:8080/api/cursos`

Console H2: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:cruddb`
- User Name: `sa`
- Password: deixe vazio

O H2 está em memória: os dados são apagados quando a aplicação é encerrada.
