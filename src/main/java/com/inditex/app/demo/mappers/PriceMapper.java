package com.inditex.app.demo.mappers;

import com.inditex.app.demo.models.Price;
import com.inditex.app.demo.dto.PriceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceDTO toDto(Price price);
}
