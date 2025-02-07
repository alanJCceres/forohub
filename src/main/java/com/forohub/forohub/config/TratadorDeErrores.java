package com.forohub.forohub.config;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErrores {
    /*@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e, HttpServletRequest request){
        var errores  = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put("message", "Se encontraron errores en la solicitud.");
        response.put("errors", errores);
        response.put("path", request.getRequestURI());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
    }
    //TRATAR TODOS LOS ERRORES NO IMPLEMENTADOS DE MANERA GENERAL
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException e, HttpServletRequest request) {
        HttpStatus status = (HttpStatus) e.getStatusCode();

        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", e.getReason());
        response.put("path", request.getRequestURI());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(status).body(response);
    }
    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity tratarErrorAtributosDesconocidosJson(
            UnrecognizedPropertyException ex,
            HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put("message", "La solicitud contiene atributos no permitidos.");
        response.put("path", request.getRequestURI());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorDeValidacion(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    private record DatosErrorValidacion (String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
