package cz.eg.hr.controller;

import cz.eg.hr.JavascriptFrameworkNotFoundException;
import cz.eg.hr.rest.Errors;
import cz.eg.hr.rest.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Errors> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<Error> errorList = result.getFieldErrors().stream()
            .map(e -> new Error(e.getField(), e.getCode()))
            .toList();

        return ResponseEntity.badRequest().body(new Errors(errorList));
    }

    @ExceptionHandler(JavascriptFrameworkNotFoundException.class)
    public ResponseEntity<Errors> handleNotFoundException(JavascriptFrameworkNotFoundException ex) {
        List<Error> errorList = Collections.singletonList(new Error("Javascript Framework not found", ex.getMessage()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Errors(errorList));
    }

}
