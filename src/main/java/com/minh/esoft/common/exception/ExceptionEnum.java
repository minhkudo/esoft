package com.minh.esoft.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionEnum {
    DATA_ALREADY_EXISTS(40000001, "Dữ liệu đã tồn tại"),
    DATA_NOT_FOUND(40400001, "Không tìm thấy dữ liệu"),
    DATA_NOT_UPDATE(40400003, "Dữ liệu không thể chỉnh sửa"),
    DATA_NOT_RELEVANT_TO_USER(40000002, "Dữ liệu không phù hợp với người dùng");
    private final int errorCode;
    private final String message;

    ExceptionEnum(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }


}
