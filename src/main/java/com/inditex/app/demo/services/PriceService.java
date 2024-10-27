package com.inditex.app.demo.services;

import com.inditex.app.demo.models.Price;
import com.inditex.app.demo.dto.PriceDTO;
import com.inditex.app.demo.mappers.PriceMapper;
import com.inditex.app.demo.repositories.PriceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public interface PriceService {
    PriceDTO getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}