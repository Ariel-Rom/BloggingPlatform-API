package com.Ariel_Rom.BloggingPlatform_API.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Indica que esta clase maneja excepciones de manera global para los controladores REST
public class GlobalExceptionHandler {

    /*
     * Maneja la excepción cuando no se encuentra una entidad (por ejemplo, al buscar un post por ID que no existe).
     * Devuelve un JSON con un mensaje y código 404.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> entityNotFoundHandler(EntityNotFoundException e) {
        Map<String, String> error = new HashMap<>();
        error.put("message", e.getMessage()); // Mensaje de la excepción
        error.put("code", "404"); // Código de error personalizado
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /*
     * Maneja errores de validación cuando una request falla al validar anotaciones como @NotNull, @Size, etc.
     * Devuelve un JSON con los campos inválidos y sus mensajes de error.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationHandler(MethodArgumentNotValidException e) {
        Map<String, String> error = new HashMap<>();

        // Por cada error de validación, guarda el nombre del campo y su mensaje de error
        e.getBindingResult().getFieldErrors().forEach(errors ->
                error.put(errors.getField(), errors.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(error); // Devuelve 400 Bad Request con los errores
    }
}
