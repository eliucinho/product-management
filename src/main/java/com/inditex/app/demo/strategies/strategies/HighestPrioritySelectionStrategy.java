package com.inditex.app.demo.strategies.strategies;

import com.inditex.app.demo.dto.PriceDTO;
import com.inditex.app.demo.mappers.PriceMapper;
import com.inditex.app.demo.models.Price;
import com.inditex.app.demo.repositories.PriceRepository;
import com.inditex.app.demo.strategies.SelectionStrategy;
import com.inditex.app.demo.strategies.inputs.PriceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HighestPrioritySelectionStrategy implements SelectionStrategy<PriceRequest, PriceDTO> {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public PriceDTO process(PriceRequest priceRequest) {
        Price price=priceRepository
                .findTopByPriceId_ProductIdAndPriceId_BrandIdAndPriceId_StartDateLessThanEqualAndPriceId_EndDateGreaterThanEqualOrderByPriorityDesc(
                        priceRequest.getProductId(), priceRequest.getBrandId(), priceRequest.getAppDate(), priceRequest.getAppDate())
                .orElseThrow(() -> new RuntimeException("No price found for the given parameters"));

        return priceMapper.toDto(price);
    }
}