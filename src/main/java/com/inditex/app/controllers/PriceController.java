package com.inditex.app.controllers;

import com.inditex.app.constanst.ErrorMessages;
import com.inditex.app.dto.PriceDTO;
import com.inditex.app.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController implements PriceApi {

    @Autowired
    private PriceService priceService;

    @Override
    public ResponseEntity<?> getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        try {
            PriceDTO priceDTO = priceService.getPrice(applicationDate, productId, brandId);
            return ResponseEntity.ok(priceDTO);
        } catch (RuntimeException e) {
            return handleNotFoundError(e);
        } catch (Exception e) {
            return handleInternalServerError(e);
        }
    }

    private ResponseEntity<String> handleNotFoundError(RuntimeException e) {
        String errorMessage = String.format("Error: %s", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    private ResponseEntity<String> handleInternalServerError(Exception e) {
        String errorMessage = String.format("%s: %s", ErrorMessages.INTERNAL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
