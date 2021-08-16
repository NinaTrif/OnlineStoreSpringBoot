package com.example.tokyotask.exceptions;

import com.example.tokyotask.util.ErrorCode;

public class BaseException extends RuntimeException {
    /**
     * This class is used to represent different exceptions.
     * Error code is used to differentiate which exception has been thrown.
     */
    private ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
