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
public class PriceServiceImpl implements PriceService {

    @Autowired
    private SelectionStrategy<PriceRequest, PriceResponse> highestPrioritySelectionStrategy;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public PriceDTO getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        PriceResponse priceResponse = highestPrioritySelectionStrategy.process(buildRequest(applicationDate,productId,brandId));

        return priceMapper.toDto(priceResponse);
    }

    private PriceRequest buildRequest(LocalDateTime applicationDate, Long productId, Long brandId){
        return PriceRequest.builder()
                .appDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();
    }
}