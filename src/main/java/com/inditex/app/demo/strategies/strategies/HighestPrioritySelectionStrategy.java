package com.inditex.app.demo.strategies.strategies;

import com.inditex.app.demo.mappers.PriceMapper;
import com.inditex.app.demo.repositories.PriceRepository;
import com.inditex.app.demo.strategies.SelectionStrategy;
import com.inditex.app.demo.strategies.inputs.PriceRequest;
import com.inditex.app.demo.strategies.outputs.PriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HighestPrioritySelectionStrategy implements SelectionStrategy<PriceRequest, PriceResponse> {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceMapper priceMapper;
    @Override
    public PriceResponse process(PriceRequest priceRequest) {
        return (PriceResponse) priceRepository
                .findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        priceRequest.getProductId(), priceRequest.getBrandId(), priceRequest.getAppDate(), priceRequest.getAppDate())
                .orElseThrow(() -> new RuntimeException("No price found for the given parameters"));
    }
}