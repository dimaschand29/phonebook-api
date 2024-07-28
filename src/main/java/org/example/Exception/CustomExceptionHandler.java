package org.example.Exception;

import org.example.Response.ResponseEntityBuilder;
import org.example.Response.ResponseEntityDto;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseEntityDto<Void>> handleValidationExceptions(HttpMessageNotReadableException ex) {
        String msg = ex.getMessage();
        return ResponseEntityBuilder.badRequestResponse(msg, null);
    }
}
