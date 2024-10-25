package com.inditex.app.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfter(
            Integer productId, Integer brandId, LocalDateTime date, LocalDateTime dateEnd);
}