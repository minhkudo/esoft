package com.minh.esoft.service;

import com.minh.esoft.repository.OrdersRepository;
import com.minh.esoft.repository.entity.OrdersEntity;
import com.minh.esoft.repository.mapper.OrdersMapper;
import com.minh.esoft.repository.request.OrderRequest;
import com.minh.esoft.repository.response.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {
    private final String PATTERN_ORDER = "ESOFT.";
    private final OrdersRepository ordersRepository;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        String orderCode = PATTERN_ORDER + Instant.now().getEpochSecond();
        OrdersEntity ordersEntity = OrdersMapper.INSTANCE.map2CreateOrderEntity(orderRequest);
        ordersEntity.setCode(orderCode);

        ordersEntity = ordersRepository.save(ordersEntity);
        return OrdersMapper.INSTANCE.map2OrderResponse(ordersEntity);
    }

}
