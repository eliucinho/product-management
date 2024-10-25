package com.inditex.app.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public ResponseEntity<PriceDTO> getPrice(@RequestParam LocalDateTime applicationDate,
                                             @RequestParam Integer productId,
                                             @RequestParam Integer brandId) {
        PriceDTO priceDTO = priceService.getPrice(applicationDate, productId, brandId);
        return ResponseEntity.ok(priceDTO);
    }
}
