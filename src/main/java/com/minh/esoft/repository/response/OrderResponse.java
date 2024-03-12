package com.minh.esoft.repository.response;

import com.minh.esoft.common.enums.OrderCategoryEnum;
import com.minh.esoft.common.enums.OrderServiceEnum;
import com.minh.esoft.common.enums.OrderStatusEnum;
import lombok.Data;

import java.time.Instant;

@Data
public class OrderResponse {
    private String code;
    private String name;
    private String description;
    private String notes;
    private OrderCategoryEnum orderCategoryCode;
    private Long quantity;
    private OrderStatusEnum orderStatus;
    private OrderServiceEnum orderServiceCode;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private String createdBy;
    private String updatedBy;
}
