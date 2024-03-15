package com.minh.esoft.repository.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SummaryOrderUserRequest {
    private Long userId;
    private Long createdAtFrom;
    private Long createdAtTo;
}
