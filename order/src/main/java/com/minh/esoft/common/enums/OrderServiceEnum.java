package com.minh.esoft.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderServiceEnum {
    PHOTO_EDITING("PHOTO_EDITING", 1.5), VIDEO_EDITING("VIDEO_EDITING", 2.0);

    private final String name;
    private final Double rate;
}
