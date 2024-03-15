package com.minh.esoft.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderCategoryEnum {
    LUXURY("LUXURY", 1000), SUPER_LUXURY("SUPER_LUXURY", 2000), SUPREME_LUXURY("SUPREME_LUXURY", 3000);

    private final String name;
    private final Integer price;

}
