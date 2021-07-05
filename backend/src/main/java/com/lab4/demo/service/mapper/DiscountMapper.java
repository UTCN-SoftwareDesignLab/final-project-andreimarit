package com.lab4.demo.service.mapper;

import com.lab4.demo.dto.DiscountDTO;
import com.lab4.demo.model.Discount;

//@Mapper(componentModel = "spring")
public interface DiscountMapper {

    DiscountDTO toDto(Discount discount);

    Discount fromDto(DiscountDTO discount);

}
