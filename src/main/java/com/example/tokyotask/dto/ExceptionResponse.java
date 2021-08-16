package com.example.tokyotask.dto;

import com.example.tokyotask.util.ErrorCode;
import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    /**
     * This class is used as DTO for exception handling.
     * It contains exception status, message and code.
     */
    private HttpStatus status;
    private String message;
    private String code;

    public static ExceptionResponse createErrorResponse(ErrorCode code) {
        return new ExceptionResponse(code.getStatus(), code.getMessage(), code.getErrorCode());
    }

    public ExceptionResponse() {
    }

    public ExceptionResponse(HttpStatus status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
