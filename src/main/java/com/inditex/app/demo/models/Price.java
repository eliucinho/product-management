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
    private PriceId id;

    @Column(name = "PRICE_LIST")
    private int priceList;

    @Column(name = "PRIORITY")
    private int priority;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "CURR")
    private String currency;
}