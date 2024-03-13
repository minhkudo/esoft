package com.minh.esoft.common.exception;

public class DataNotRelevantToUserException extends ApplicationException {
    public DataNotRelevantToUserException() {
        super(ExceptionEnum.DATA_NOT_RELEVANT_TO_USER);
    }

    public DataNotRelevantToUserException(String message) {
        super(ExceptionEnum.DATA_NOT_RELEVANT_TO_USER.getErrorCode(), message);
    }
}
