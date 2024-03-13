package com.minh.esoft.repository.specifications;

import com.minh.esoft.repository.entity.OrdersEntity;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class OrderSpecification {

    public Specification<OrdersEntity> userId(String userId) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(userId)) {
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
}
