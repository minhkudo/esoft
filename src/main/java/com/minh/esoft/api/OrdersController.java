package com.minh.esoft.api;

import com.minh.esoft.common.BaseResponse;
import com.minh.esoft.common.enums.OrderCategoryEnum;
import com.minh.esoft.common.enums.OrderServiceEnum;
import com.minh.esoft.common.exception.DataNotFoundException;
import com.minh.esoft.common.exception.DataNotRelevantToUserException;
import com.minh.esoft.common.exception.DataNotUpdateException;
import com.minh.esoft.repository.request.OrderCreateRequest;
import com.minh.esoft.repository.request.OrderQueryRequest;
import com.minh.esoft.repository.request.OrderUpdateRequest;
import com.minh.esoft.repository.response.OrderResponse;
import com.minh.esoft.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrder(
            @RequestParam(required = false) @Min(0) int page,
            @RequestParam(required = false) @Min(5) int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) OrderCategoryEnum orderCategoryEnum,
            @RequestParam(required = false) OrderServiceEnum orderServiceCode,
            @RequestParam(required = false) Long createdAtFrom,
            @RequestParam(required = false) Long createdAtTo,
            @RequestParam(required = false) Long updatedAtFrom,
            @RequestParam(required = false) Long updatedAtTo

    ) {
        OrderQueryRequest orderQueryRequest = OrderQueryRequest.builder()
                .name(name)
                .description(description)
                .orderCategoryEnum(orderCategoryEnum)
                .orderServiceCode(orderServiceCode)
                .createdAtFrom(createdAtFrom)
                .createdAtTo(createdAtTo)
                .build();
        Page<OrderResponse> pageResponse = orderService.getOrder(page, size, orderQueryRequest);
        return BaseResponse.success(pageResponse);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderCreateRequest orderCreateRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderCreateRequest);
        return BaseResponse.success(orderResponse);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderUpdateRequest orderUpdateRequest) throws DataNotFoundException, DataNotRelevantToUserException, DataNotUpdateException {
        OrderResponse orderResponse = orderService.updateOrder(id, orderUpdateRequest);
        return BaseResponse.success(orderResponse);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) throws DataNotFoundException, DataNotUpdateException, DataNotRelevantToUserException {
        boolean isDelete = orderService.deleteOrder(id);
        return BaseResponse.success(isDelete);
    }
}
