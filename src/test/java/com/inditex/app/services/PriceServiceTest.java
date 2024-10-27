package com.inditex.app.services;

import com.inditex.app.dto.PriceDTO;
import com.inditex.app.strategies.SelectionStrategy;
import com.inditex.app.strategies.inputs.PriceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PriceServiceTest {

    @Mock
    private SelectionStrategy<PriceRequest, PriceDTO> highestPrioritySelectionStrategy;

    @InjectMocks
    private PriceService priceService;

    private PriceRequest priceRequest;
    private PriceDTO expectedPriceDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        priceRequest = new PriceRequest(LocalDateTime.of(2020, 6, 14, 10, 0), 35455L, 1L);

        expectedPriceDTO = new PriceDTO();
        expectedPriceDTO.setProductId(35455);
        expectedPriceDTO.setBrandId(1);
        expectedPriceDTO.setPrice(35.50);
        expectedPriceDTO.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        expectedPriceDTO.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        expectedPriceDTO.setPriceList(1);
    }

    @Test
    void getPrice_shouldReturnCorrectPriceDTO() {
        when(highestPrioritySelectionStrategy.process(any(PriceRequest.class))).thenReturn(expectedPriceDTO);

        PriceDTO result = priceService.getPrice(LocalDateTime.of(2020, 6, 14, 10, 0), 35455L, 1L);

        verify(highestPrioritySelectionStrategy, times(1)).process(any(PriceRequest.class));
        assertEquals(expectedPriceDTO, result);
    }
}
