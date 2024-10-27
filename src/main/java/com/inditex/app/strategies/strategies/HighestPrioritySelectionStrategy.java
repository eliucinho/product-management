package com.inditex.app.strategies.strategies;

import com.inditex.app.constanst.ErrorMessages;
import com.inditex.app.dto.PriceDTO;
import com.inditex.app.mappers.PriceMapper;
import com.inditex.app.repositories.PriceRepository;
import com.inditex.app.strategies.SelectionStrategy;
import com.inditex.app.strategies.inputs.PriceRequest;
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
        return priceRepository
                .findTopByPriceId_ProductIdAndPriceId_BrandIdAndPriceId_StartDateLessThanEqualAndPriceId_EndDateGreaterThanEqualOrderByPriorityDesc(
                        priceRequest.getProductId(), priceRequest.getBrandId(), priceRequest.getAppDate(), priceRequest.getAppDate())
                .map(priceMapper::toDto)
                .orElseThrow(() -> new RuntimeException(ErrorMessages.PRICE_NOT_FOUND));
    }
}