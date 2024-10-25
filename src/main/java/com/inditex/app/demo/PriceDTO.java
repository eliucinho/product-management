package com.inditex.app.demo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PriceDTO {
    private Integer productId;
    private Integer brandId;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
}