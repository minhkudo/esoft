package com.minh.esoft.common.exception;

public class DataNotUpdateException extends ApplicationException {
    public DataNotUpdateException() {
        super(ExceptionEnum.DATA_NOT_UPDATE);
    }

    public DataNotUpdateException(String message) {
        super(ExceptionEnum.DATA_NOT_UPDATE.getErrorCode(), message);
    }
}
