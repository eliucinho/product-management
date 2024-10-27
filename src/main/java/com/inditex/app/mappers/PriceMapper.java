package com.inditex.app.mappers;

import com.inditex.app.models.Price;
import com.inditex.app.dto.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "priceId.productId", target = "productId")
    @Mapping(source = "priceId.brandId", target = "brandId")
    @Mapping(source = "priceId.startDate", target = "startDate")
    @Mapping(source = "priceId.endDate", target = "endDate")
    PriceDTO toDto(Price price);

}
