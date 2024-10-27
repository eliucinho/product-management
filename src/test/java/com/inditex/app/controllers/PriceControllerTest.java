package com.inditex.app.controllers;

import com.inditex.app.constanst.ErrorMessages;
import com.inditex.app.dto.PriceDTO;
import com.inditex.app.services.PriceService;
import com.inditex.app.strategies.inputs.PriceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

class PriceControllerTest {

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private PriceDTO priceDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        priceDTO = createDefaultPriceDTO();
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(createPriceRequest(LocalDateTime.of(2020, 6, 14, 10, 0)), "Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)", 35.50),
                Arguments.of(createPriceRequest(LocalDateTime.of(2020, 6, 14, 16, 0)), "Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)", 25.45),
                Arguments.of(createPriceRequest(LocalDateTime.of(2020, 6, 14, 21, 0)), "Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)", 35.50),
                Arguments.of(createPriceRequest(LocalDateTime.of(2020, 6, 15, 10, 0)), "Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)", 30.50),
                Arguments.of(createPriceRequest(LocalDateTime.of(2020, 6, 16, 21, 0)), "Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)", 38.95)
        );
    }

    @ParameterizedTest(name = "{1}")
    @MethodSource("provideTestCases")
    void testGetPrice(PriceRequest priceRequest, String testCaseDescription, Double expectedPrice) {
        System.out.println(testCaseDescription);
        priceDTO.setPrice(expectedPrice);
        when(priceService.getPrice(priceRequest.getAppDate(), priceRequest.getProductId(), priceRequest.getBrandId()))
                .thenReturn(priceDTO);

        ResponseEntity<?> response = priceController.getPrice(
                priceRequest.getAppDate(), priceRequest.getProductId(), priceRequest.getBrandId());

        verify(priceService, times(1))
                .getPrice(priceRequest.getAppDate(), priceRequest.getProductId(), priceRequest.getBrandId());

        assertEquals(OK, response.getStatusCode(), "Expected HTTP status 200 OK");
        PriceDTO responseBody = (PriceDTO) response.getBody();
        assertEquals(expectedPrice, responseBody.getPrice(), "Expected price to match");

        System.out.println("Test passed: " + testCaseDescription + " with expected price: " + expectedPrice);
    }

    @Test
    void testGetPriceThrowsRuntimeException() {
        PriceRequest priceRequest = createPriceRequest(LocalDateTime.of(2020, 6, 14, 10, 0));
        String errorMessage = "No price found for the given parameters";

        when(priceService.getPrice(priceRequest.getAppDate(), priceRequest.getProductId(), priceRequest.getBrandId()))
                .thenThrow(new RuntimeException(errorMessage));

        ResponseEntity<?> response = priceController.getPrice(
                priceRequest.getAppDate(), priceRequest.getProductId(), priceRequest.getBrandId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Expected HTTP status 404 Not Found");
        assertEquals("Error: " + errorMessage, response.getBody(), "Expected error message to match");
    }

    private static PriceRequest createPriceRequest(LocalDateTime appDate) {
        return new PriceRequest(appDate, PRODUCT_ID, BRAND_ID);
    }

    private PriceDTO createDefaultPriceDTO() {
        PriceDTO dto = new PriceDTO();
        dto.setProductId(35455);
        dto.setBrandId(1);
        dto.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        dto.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        dto.setPriceList(1);
        return dto;
    }
}
