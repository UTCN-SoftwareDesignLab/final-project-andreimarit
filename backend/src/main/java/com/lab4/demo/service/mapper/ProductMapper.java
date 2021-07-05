package com.lab4.demo.service.mapper;

import com.lab4.demo.dto.ProductDTO;
import com.lab4.demo.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto(Product product);

    Product fromDto(ProductDTO product);

}
