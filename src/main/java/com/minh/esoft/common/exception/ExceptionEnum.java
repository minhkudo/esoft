package com.minh.esoft.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    DATA_ALREADY_EXISTS(40000001, "Dữ liệu đã tồn tại"),
    DATA_NOT_FOUND(40400001, "Không tìm thấy dữ liệu");
    private final int errorCode;
    private final String message;

    ExceptionEnum(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }


}
