package com.inditex.app.demo.strategies.inputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PriceRequest {
    private LocalDateTime appDate;
    private Long productId;
    private Long brandId;
}
