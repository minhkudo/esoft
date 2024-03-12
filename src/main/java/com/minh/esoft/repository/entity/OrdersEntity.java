package com.minh.esoft.repository.entity;

import com.minh.esoft.common.enums.OrderCategoryEnum;
import com.minh.esoft.common.enums.OrderServiceEnum;
import com.minh.esoft.common.enums.OrderStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrdersEntity extends BaseEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    @Column(name = "order_category_code")
    private OrderCategoryEnum orderCategoryCode;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "order_status")
    private OrderStatusEnum orderStatus;

    @Column(name = "order_service_code")
    private OrderServiceEnum orderServiceCode;

    @Column(name = "user_id")
    private Long userId;
}
