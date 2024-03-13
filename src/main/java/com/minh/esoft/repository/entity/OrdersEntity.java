package com.minh.esoft.repository.entity;

import com.minh.esoft.auth.JwtUserDetail;
import com.minh.esoft.common.enums.OrderCategoryEnum;
import com.minh.esoft.common.enums.OrderServiceEnum;
import com.minh.esoft.common.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrdersEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "orders_category_code")
    private OrderCategoryEnum orderCategoryCode;

    @Column(name = "quantity")
    private Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "orders_status")
    private OrderStatusEnum orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "orders_service_code")
    private OrderServiceEnum orderServiceCode;

    @Column(name = "user_id")
    private Long userId;

    @PrePersist
    public void prePersist() {
        JwtUserDetail jwtUserDetail = (JwtUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userId = jwtUserDetail.getAccountId();
    }
}
