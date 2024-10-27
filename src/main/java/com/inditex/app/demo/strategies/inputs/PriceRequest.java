package com.inditex.app.demo.strategies.inputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PriceRequest {
    private LocalDateTime appDate;
    private Long productId;
    private Long brandId;
}
