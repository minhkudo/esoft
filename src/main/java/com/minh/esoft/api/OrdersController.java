package com.minh.esoft.api;

import com.minh.esoft.common.BaseResponse;
import com.minh.esoft.repository.request.OrderRequest;
import com.minh.esoft.repository.response.OrderResponse;
import com.minh.esoft.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
public class OrdersController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrder() {
        return BaseResponse.success("orderResponse");
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return BaseResponse.success(orderResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return BaseResponse.success(orderResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
//        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return BaseResponse.success("orderResponse");
    }
}
