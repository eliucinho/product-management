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
    Optional<Price> findTopByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            int productId, int brandId, LocalDateTime startDate, LocalDateTime endDate);
}