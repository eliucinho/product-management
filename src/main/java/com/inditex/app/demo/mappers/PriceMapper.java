package com.inditex.app.demo.mappers;

import com.inditex.app.demo.models.Price;
import com.inditex.app.demo.dto.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "priceId.productId", target = "productId")
    @Mapping(source = "priceId.brandId", target = "brandId")
    @Mapping(source = "priceId.startDate", target = "startDate")
    @Mapping(source = "priceId.endDate", target = "endDate")
    PriceDTO toDto(Price price);

    default Double mapPriceToDouble(Price price) {
        return price != null ? price.getPrice() : null;
    }
}
