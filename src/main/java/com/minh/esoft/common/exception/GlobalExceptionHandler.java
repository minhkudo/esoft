package com.minh.esoft.common.exception;

import com.minh.esoft.common.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ApplicationException.class})  // Có thể bắt nhiều loại exception
    public ResponseEntity<?> handleExceptionA(ApplicationException e) {
        return BaseResponse.error(e);
    }
}
