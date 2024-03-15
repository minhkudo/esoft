package com.minh.esoft.api;

import com.minh.esoft.common.BaseResponse;
import com.minh.esoft.common.exception.DataNotFoundException;
import com.minh.esoft.common.exception.DataNotRelevantToUserException;
import com.minh.esoft.common.exception.DataNotUpdateException;
import com.minh.esoft.repository.request.OrderCreateRequest;
import com.minh.esoft.repository.request.OrderUpdateRequest;
import com.minh.esoft.repository.request.SummaryOrderUserRequest;
import com.minh.esoft.repository.response.OrderResponse;
import com.minh.esoft.service.ReportService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class ReportsController {
    private final ReportService reportService;

    @GetMapping("/summary-order-user")
    public ResponseEntity<?> getSummaryOrderUser(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long createdAtFrom,
            @RequestParam(required = false) Long createdAtTo
    ) {
        SummaryOrderUserRequest summaryOrderUserRequest = SummaryOrderUserRequest.builder()
                .userId(userId)
                .createdAtFrom(createdAtFrom)
                .createdAtTo(createdAtTo)
                .build();
        Map<String, Object> response = reportService.getSummaryOrderUser(summaryOrderUserRequest);
        return BaseResponse.success(response);
    }
//
//    @PostMapping
//    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderCreateRequest orderCreateRequest) {
//        OrderResponse orderResponse = orderService.createOrder(orderCreateRequest);
//        return BaseResponse.success(orderResponse);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody @Valid OrderUpdateRequest orderUpdateRequest) throws DataNotFoundException, DataNotRelevantToUserException, DataNotUpdateException {
//        OrderResponse orderResponse = orderService.updateOrder(id, orderUpdateRequest);
//        return BaseResponse.success(orderResponse);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteOrder(@PathVariable Long id) throws DataNotFoundException, DataNotUpdateException, DataNotRelevantToUserException {
//        boolean isDelete = orderService.deleteOrder(id);
//        return BaseResponse.success(isDelete);
//    }
}
