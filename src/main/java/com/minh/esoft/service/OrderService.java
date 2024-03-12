package com.minh.esoft.service;

import com.minh.esoft.common.BaseResponse;
import com.minh.esoft.repository.OrdersRepository;
import com.minh.esoft.repository.request.OrderRequest;
import com.minh.esoft.repository.response.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        return new OrderResponse();
    }

}
