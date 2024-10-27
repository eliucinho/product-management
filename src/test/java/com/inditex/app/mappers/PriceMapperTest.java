package com.inditex.app.mappers;

import com.inditex.app.dto.PriceDTO;
import com.inditex.app.models.Price;
import com.inditex.app.models.PriceId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceMapperTest {

    private PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

    private Price price;
    private PriceId priceId;

    @BeforeEach
    void setUp() {
        priceId = new PriceId();
        priceId.setProductId(35455L);
        priceId.setBrandId(1L);
        priceId.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        priceId.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));

        price = new Price();
        price.setPriceId(priceId);
        price.setPrice(35.50);
        price.setPriceList(1);
    }

    @Test
    void toDto_shouldMapPriceToPriceDTO() {
        PriceDTO priceDTO = priceMapper.toDto(price);

        assertEquals(priceId.getProductId().intValue(), priceDTO.getProductId());
        assertEquals(priceId.getBrandId().intValue(), priceDTO.getBrandId());
        assertEquals(price.getPriceList(), priceDTO.getPriceList());
        assertEquals(price.getPrice(), priceDTO.getPrice());
        assertEquals(priceId.getStartDate(), priceDTO.getStartDate());
        assertEquals(priceId.getEndDate(), priceDTO.getEndDate());
    }
}
