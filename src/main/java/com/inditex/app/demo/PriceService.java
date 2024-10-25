package com.inditex.app.demo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceMapper priceMapper;

    public PriceDTO getPrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        List<Price> prices = priceRepository.findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
                productId, brandId, applicationDate, applicationDate);

        return prices.stream()
                .max(Comparator.comparing(Price::getPriority))
                .map(priceMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Price not found"));
    }
}