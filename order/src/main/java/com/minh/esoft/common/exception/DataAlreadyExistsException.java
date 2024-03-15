package com.minh.esoft.common.exception;

public class DataAlreadyExistsException extends ApplicationException {
    public DataAlreadyExistsException() {
        super(ExceptionEnum.DATA_ALREADY_EXISTS);
    }

    public DataAlreadyExistsException(String message) {
        super(ExceptionEnum.DATA_ALREADY_EXISTS.getErrorCode(), message);
    }
}
