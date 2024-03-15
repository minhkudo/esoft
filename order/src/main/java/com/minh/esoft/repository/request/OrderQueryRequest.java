package com.minh.esoft.repository.request;

import com.minh.esoft.common.enums.OrderCategoryEnum;
import com.minh.esoft.common.enums.OrderServiceEnum;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderQueryRequest {
    private String name;
    private String description;
    private OrderCategoryEnum orderCategoryEnum;
    private OrderServiceEnum orderServiceCode;
    private Long createdAtFrom;
    private Long createdAtTo;
    private Long updatedAtFrom;
    private Long updatedAtTo;
}
