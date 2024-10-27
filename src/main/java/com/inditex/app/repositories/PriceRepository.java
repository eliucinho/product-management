package com.inditex.app.repositories;

import com.inditex.app.models.Price;
import com.inditex.app.models.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, PriceId> {
    Optional<Price> findTopByPriceId_ProductIdAndPriceId_BrandIdAndPriceId_StartDateLessThanEqualAndPriceId_EndDateGreaterThanEqualOrderByPriorityDesc(
            Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate);

}