package com.minh.esoft.repository.request;

import com.minh.esoft.common.enums.OrderCategoryEnum;
import com.minh.esoft.common.enums.OrderServiceEnum;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.Instant;

@Data
public class OrderQueryRequest {
    @Min(0)
    private Long page;
    @Min(5)
    private Long size;
    private String name;
    private String description;
    private OrderCategoryEnum orderCategoryEnum;
    private OrderServiceEnum orderServiceCode;
    private Long createdAtFrom;
    private Long createdAtTo;
    private Long updatedAtFrom;
    private Long updatedAtTo;
}
