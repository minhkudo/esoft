package com.minh.esoft.api;

import com.minh.esoft.common.BaseResponse;
import com.minh.esoft.common.exception.DataNotFoundException;
import com.minh.esoft.common.exception.DataNotRelevantToUserException;
import com.minh.esoft.common.exception.DataNotUpdateException;
import com.minh.esoft.repository.request.OrderCreateRequest;
import com.minh.esoft.repository.request.OrderQueryRequest;
import com.minh.esoft.repository.request.OrderUpdateRequest;
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
    public ResponseEntity<?> getOrder(@RequestParam OrderQueryRequest orderQueryRequest) {
        return BaseResponse.success("orderResponse");
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderCreateRequest orderCreateRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderCreateRequest);
        return BaseResponse.success(orderResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderUpdateRequest orderUpdateRequest) throws DataNotFoundException, DataNotRelevantToUserException, DataNotUpdateException {
        OrderResponse orderResponse = orderService.updateOrder(id, orderUpdateRequest);
        return BaseResponse.success(orderResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) throws DataNotFoundException, DataNotUpdateException, DataNotRelevantToUserException {
        boolean isDelete = orderService.deleteOrder(id);
        return BaseResponse.success(isDelete);
    }
}
