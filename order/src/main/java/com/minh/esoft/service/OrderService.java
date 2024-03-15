package com.minh.esoft.service;

import com.minh.esoft.auth.JwtUserDetail;
import com.minh.esoft.common.enums.OrderStatusEnum;
import com.minh.esoft.common.exception.DataNotFoundException;
import com.minh.esoft.common.exception.DataNotRelevantToUserException;
import com.minh.esoft.common.exception.DataNotUpdateException;
import com.minh.esoft.repository.OrdersRepository;
import com.minh.esoft.repository.entity.OrdersEntity;
import com.minh.esoft.repository.mapper.OrdersMapper;
import com.minh.esoft.repository.request.OrderCreateRequest;
import com.minh.esoft.repository.request.OrderQueryRequest;
import com.minh.esoft.repository.request.OrderUpdateRequest;
import com.minh.esoft.repository.response.OrderResponse;
import com.minh.esoft.repository.specifications.OrderSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {
    private final String PATTERN_ORDER = "ESOFT.";
    private final OrdersRepository ordersRepository;

    public Page<OrderResponse> getOrder(int page, int size, OrderQueryRequest orderQueryRequest) {
        Specification<OrdersEntity> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        JwtUserDetail jwtUserDetail = (JwtUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (jwtUserDetail.getAccountId() != null) {
            specs = specs.and(OrderSpecification.userId(jwtUserDetail.getAccountId()));
        }
        if (StringUtils.hasText(orderQueryRequest.getName())) {
            specs = specs.and(OrderSpecification.name(orderQueryRequest.getName()));
        }
        if (StringUtils.hasText(orderQueryRequest.getDescription())) {
            specs = specs.and(OrderSpecification.description(orderQueryRequest.getDescription()));
        }
        if (orderQueryRequest.getCreatedAtFrom() != null) {
            specs = specs.and(OrderSpecification.createdAtFrom(Instant.ofEpochSecond(orderQueryRequest.getCreatedAtFrom())));
        }
        if (orderQueryRequest.getCreatedAtTo() != null) {
            specs = specs.and(OrderSpecification.createdAtFrom(Instant.ofEpochSecond(orderQueryRequest.getCreatedAtTo())));
        }

        Pageable pageable = PageRequest.of(page, size);
        return ordersRepository.findAll(specs, pageable)
                .map(OrdersMapper.INSTANCE::map2OrderResponse);
    }

    public OrderResponse createOrder(OrderCreateRequest orderCreateRequest) {
        String orderCode = PATTERN_ORDER + Instant.now().getEpochSecond();
        OrdersEntity ordersEntity = OrdersMapper.INSTANCE.map2CreateOrderEntity(orderCreateRequest);
        ordersEntity.setCode(orderCode);
        ordersEntity.setOrderStatus(OrderStatusEnum.INITIAL);

        ordersEntity = ordersRepository.save(ordersEntity);
        return OrdersMapper.INSTANCE.map2OrderResponse(ordersEntity);
    }

    public OrderResponse updateOrder(Long id, OrderUpdateRequest orderUpdateRequest) throws DataNotFoundException, DataNotRelevantToUserException, DataNotUpdateException {
        Optional<OrdersEntity> optional = ordersRepository.findById(id);
        if (optional.isEmpty()) {
            throw new DataNotFoundException();
        }

        OrdersEntity ordersEntity = optional.get();
        JwtUserDetail jwtUserDetail = (JwtUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(ordersEntity.getUserId(), jwtUserDetail.getAccountId())) {
            throw new DataNotRelevantToUserException();
        }

        if (ordersEntity.getOrderStatus() != OrderStatusEnum.INITIAL) {
            throw new DataNotUpdateException("Không thể thay đổi đơn hàng");
        }

        OrdersMapper.INSTANCE.mapToOrderEntity(ordersEntity, orderUpdateRequest);

        ordersEntity = ordersRepository.save(ordersEntity);
        return OrdersMapper.INSTANCE.map2OrderResponse(ordersEntity);
    }

    public boolean deleteOrder(Long id) throws DataNotFoundException, DataNotRelevantToUserException, DataNotUpdateException {
        Optional<OrdersEntity> optional = ordersRepository.findById(id);
        if (optional.isEmpty()) {
            throw new DataNotFoundException();
        }

        OrdersEntity ordersEntity = optional.get();
        JwtUserDetail jwtUserDetail = (JwtUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(ordersEntity.getUserId(), jwtUserDetail.getAccountId())) {
            throw new DataNotRelevantToUserException();
        }

        if (ordersEntity.getOrderStatus() != OrderStatusEnum.INITIAL) {
            throw new DataNotUpdateException("Không thể thay đổi đơn hàng");
        }

        ordersEntity.setOrderStatus(OrderStatusEnum.CANCELLED);
        ordersEntity.setDeletedAt(Instant.now());
        ordersEntity = ordersRepository.save(ordersEntity);
        return ordersEntity.getDeletedAt() != null;
    }

}
