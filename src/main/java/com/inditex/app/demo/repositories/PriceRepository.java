package com.inditex.app.demo.repositories;

import com.inditex.app.demo.models.Price;
import com.inditex.app.demo.models.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {
    Optional<Price> findTopByPriceId_ProductIdAndPriceId_BrandIdAndPriceId_StartDateLessThanEqualAndPriceId_EndDateGreaterThanEqualOrderByPriorityDesc(
            Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate);

}