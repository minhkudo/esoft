package com.minh.esoft.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ApplicationException extends Exception {
    private int errorCode;
    private String message;

    public ApplicationException(ExceptionEnum exceptionEnum) {
        this.errorCode = exceptionEnum.getErrorCode();
        this.message = exceptionEnum.getMessage();
    }
}
