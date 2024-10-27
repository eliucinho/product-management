package com.inditex.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Details about the price for a product")
public class PriceDTO {

    @Schema(description = "The product ID", example = "35455", required = true)
    private Integer productId;

    @Schema(description = "The brand (chain) ID", example = "1", required = true)
    private Integer brandId;

    @Schema(description = "The ID of the price list that applies", example = "1", required = true)
    private Integer priceList;

    @Schema(description = "The start date of the price application period", example = "2020-06-14T00:00:00", required = true, type = "string", format = "date-time")
    private LocalDateTime startDate;

    @Schema(description = "The end date of the price application period", example = "2020-12-31T23:59:59", required = true, type = "string", format = "date-time")
    private LocalDateTime endDate;

    @Schema(description = "The price that applies for the product in the specified time range", example = "35.50", required = true)
    private Double price;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}