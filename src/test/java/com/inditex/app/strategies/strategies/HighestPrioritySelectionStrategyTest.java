package com.inditex.app.strategies.strategies;

import com.inditex.app.constanst.ErrorMessages;
import com.inditex.app.dto.PriceDTO;
import com.inditex.app.mappers.PriceMapper;
import com.inditex.app.models.Price;
import com.inditex.app.repositories.PriceRepository;
import com.inditex.app.strategies.inputs.PriceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class HighestPrioritySelectionStrategyTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private HighestPrioritySelectionStrategy highestPrioritySelectionStrategy;

    private PriceRequest priceRequest;
    private Price price;
    private PriceDTO priceDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        priceRequest = new PriceRequest(LocalDateTime.of(2020, 6, 14, 10, 0), 35455L, 1L);

        price = new Price();
        // Set necessary fields for Price model (skipping here for brevity)

        priceDTO = new PriceDTO();
        priceDTO.setProductId(35455);
        priceDTO.setBrandId(1);
        priceDTO.setPrice(35.50);
        priceDTO.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        priceDTO.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        priceDTO.setPriceList(1);
    }

    @Test
    void process_shouldReturnCorrectPriceDTO_whenPriceExists() {
        when(priceRepository.findTopByPriceId_ProductIdAndPriceId_BrandIdAndPriceId_StartDateLessThanEqualAndPriceId_EndDateGreaterThanEqualOrderByPriorityDesc(
                anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Optional.of(price));

        when(priceMapper.toDto(price)).thenReturn(priceDTO);

        PriceDTO result = highestPrioritySelectionStrategy.process(priceRequest);

        verify(priceRepository, times(1)).findTopByPriceId_ProductIdAndPriceId_BrandIdAndPriceId_StartDateLessThanEqualAndPriceId_EndDateGreaterThanEqualOrderByPriorityDesc(
                anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));

        verify(priceMapper, times(1)).toDto(price);

        assertEquals(priceDTO, result);
    }

    @Test
    void process_shouldThrowException_whenPriceNotFound() {
        when(priceRepository.findTopByPriceId_ProductIdAndPriceId_BrandIdAndPriceId_StartDateLessThanEqualAndPriceId_EndDateGreaterThanEqualOrderByPriorityDesc(
                anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            highestPrioritySelectionStrategy.process(priceRequest);
        });

        assertEquals(ErrorMessages.PRICE_NOT_FOUND, exception.getMessage());
    }
}
