package com.inditex.app.controllers;

import com.inditex.app.dto.PriceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

public interface PriceApi {

    @Operation(
            summary = "Get price for a product",
            description = "Retrieve price information for a given product, brand, and date.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Price information retrieved",
                            content = @Content(schema = @Schema(implementation = PriceDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Price not found for given parameters"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/api/v1/prices")
    ResponseEntity<?> getPrice(
            @Parameter(description = "The date and time for which the price should be applied.", required = true, example = "2020-06-14T16:55:50")
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,

            @Parameter(description = "The ID of the product.", required = true, example = "35455")
            @RequestParam("productId") Long productId,

            @Parameter(description = "The ID of the brand (chain).", required = true, example = "1")
            @RequestParam("brandId") Long brandId
    );
}
