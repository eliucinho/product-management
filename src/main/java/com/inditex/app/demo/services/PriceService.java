package com.inditex.app.demo.services;

import com.inditex.app.demo.dto.PriceDTO;
import com.inditex.app.demo.mappers.PriceMapper;
import com.inditex.app.demo.strategies.SelectionStrategy;
import com.inditex.app.demo.strategies.inputs.PriceRequest;
import com.inditex.app.demo.strategies.outputs.PriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService{

    @Autowired
    private SelectionStrategy<PriceRequest, PriceResponse> highestPrioritySelectionStrategy;

    @Autowired
    private PriceMapper priceMapper;

    public PriceDTO getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        PriceResponse priceResponse = highestPrioritySelectionStrategy.process(buildRequest(applicationDate,productId,brandId));

        return priceMapper.toDto(priceResponse);
    }

    private PriceRequest buildRequest(LocalDateTime applicationDate, Long productId, Long brandId){
        return new PriceRequest(applicationDate,productId,brandId);
    }
}