package com.portfolio.sistemaDeAgendamento.exception;

import com.portfolio.sistemaDeAgendamento.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthException e) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .build(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .statusCode(HttpStatus.UNPROCESSABLE_CONTENT.value())
                        .build(),
                HttpStatus.UNPROCESSABLE_CONTENT
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException e) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build()
                ,
                HttpStatus.CONFLICT
        );
    }




}
