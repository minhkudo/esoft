package com.minh.esoft.repository.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minh.esoft.common.enums.OrderCategoryEnum;
import com.minh.esoft.common.enums.OrderServiceEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderUpdateRequest {

    private String description;
    private String notes;
    @NotNull
    private OrderCategoryEnum orderCategoryCode;
    @Min(1)
    private Long quantity;
    @NotNull
    private OrderServiceEnum orderServiceCode;
}
