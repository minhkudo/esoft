package com.minh.esoft.repository.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minh.esoft.common.enums.OrderCategoryEnum;
import com.minh.esoft.common.enums.OrderServiceEnum;
import com.minh.esoft.common.enums.OrderStatusEnum;
import com.minh.esoft.common.enums.validation.EnumNamePattern;
import com.minh.esoft.common.enums.validation.ValueOfEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class OrderCreateRequest {
    @NotNull
    private String name;
    private String description;
    private String notes;
    @NotNull
//    @EnumNamePattern(regexp = "SUPER_LUXURY|SUPREME_LUXURY|LUXURY")
    @ValueOfEnum(enumClass = OrderCategoryEnum.class)
    private OrderCategoryEnum orderCategoryCode;
    @Min(1)
    private Long quantity;
    @NotNull
    @EnumNamePattern(regexp = "PHOTO_EDITING|VIDEO_EDITING")
    private OrderServiceEnum orderServiceCode;
}
