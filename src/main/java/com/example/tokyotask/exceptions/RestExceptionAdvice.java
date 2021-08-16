package com.example.tokyotask.exceptions;

import com.example.tokyotask.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionAdvice {
    /**
     * Allows exception handling across whole application.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleBaseException(BaseException ex) {
        ExceptionResponse exceptionResponse = ExceptionResponse.createErrorResponse(ex.getErrorCode());
        return new ResponseEntity(exceptionResponse, exceptionResponse.getStatus());
    }
}
