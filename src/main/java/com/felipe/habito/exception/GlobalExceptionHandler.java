package com.felipe.habito.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        // Coleta as mensagens em uma lista
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();


        StandardError error = new StandardError(
                Instant.now(),           // momento exato do erro
                400,                     // status code HTTP
                "Validation Error",      // categoria do erro
                errors,            // mensagem detalhada do erro
                request.getRequestURI()   // path: "/habitos"
        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandardError> handleResponseStatusException(
            ResponseStatusException ex,
            HttpServletRequest request
    ) {
        // Extraímos o status de forma segura do ResponseStatusException
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());

        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                List.of(ex.getReason()),
                request.getRequestURI()
        );
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }
}

