package com.minh.esoft.repository.specifications;

import com.minh.esoft.common.ultils.DateTimeUltis;
import com.minh.esoft.repository.entity.OrdersEntity;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

@UtilityClass
public class OrderSpecification {

    public Specification<OrdersEntity> userId(Long userId) {
        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("userId"), userId);
        };
    }

    public Specification<OrdersEntity> name(String name) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(name)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    public Specification<OrdersEntity> description(String description) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(description)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("description"), "%" + description + "%");
        };
    }

    public Specification<OrdersEntity> createdAtFrom(Instant instant) {
        return (root, query, criteriaBuilder) -> {
            if (instant == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), DateTimeUltis.convertInstant2LocalDatetime(instant));
        };
    }

    public Specification<OrdersEntity> createdAtTo(Instant instant) {
        return (root, query, criteriaBuilder) -> {
            if (instant == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), DateTimeUltis.convertInstant2LocalDatetime(instant));
        };
    }
}
