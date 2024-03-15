package com.minh.esoft.common.exception;

public class DataNotFoundException extends ApplicationException {
    public DataNotFoundException() {
        super(ExceptionEnum.DATA_NOT_FOUND);
    }

    public DataNotFoundException(String message) {
        super(ExceptionEnum.DATA_NOT_FOUND.getErrorCode(), message);
    }
}
