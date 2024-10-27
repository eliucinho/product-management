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

    public PriceRequest(LocalDateTime appDate, Long productId, Long brandId) {
        this.appDate = appDate;
        this.productId = productId;
        this.brandId = brandId;
    }

    public LocalDateTime getAppDate() {
        return appDate;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getBrandId() {
        return brandId;
    }
}
