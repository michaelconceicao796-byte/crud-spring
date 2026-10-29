package crud.angular.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import crud.angular.demo.excepition.RecordNotFoundExcepition;

@RestControllerAdvice
public class AplicationControllerAdvice {
    
    @ExceptionHandler(RecordNotFoundExcepition.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleRecordNotFoundException(RecordNotFoundExcepition ex) {
        return ex.getMessage();
    }
}
