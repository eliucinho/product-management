package com.inditex.app.services;

import com.inditex.app.dto.PriceDTO;
import com.inditex.app.mappers.PriceMapper;
import com.inditex.app.strategies.SelectionStrategy;
import com.inditex.app.strategies.inputs.PriceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService{

    @Autowired
    private SelectionStrategy<PriceRequest, PriceDTO> highestPrioritySelectionStrategy;

    @Autowired
    private PriceMapper priceMapper;

    public PriceDTO getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return highestPrioritySelectionStrategy.process(buildRequest(applicationDate,productId,brandId));
    }

    private PriceRequest buildRequest(LocalDateTime applicationDate, Long productId, Long brandId){
        return new PriceRequest(applicationDate,productId,brandId);
    }
}