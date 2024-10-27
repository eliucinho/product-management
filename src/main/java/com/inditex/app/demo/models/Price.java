package com.inditex.app.demo.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @EmbeddedId
    private PriceId priceId;

    @Column(name = "PRICE_LIST")
    private int priceList;

    @Column(name = "PRIORITY")
    private int priority;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CURR")
    private String currency;

    public PriceId getPriceId() {
        return priceId;
    }

    public int getPriceList() {
        return priceList;
    }

    public int getPriority() {
        return priority;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}