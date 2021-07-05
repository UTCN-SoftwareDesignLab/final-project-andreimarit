package com.lab4.demo.service.mapper;


import com.lab4.demo.dto.DiscountDTO;
import com.lab4.demo.model.Discount;
import com.lab4.demo.repo.DiscountRepository;
import com.lab4.demo.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@AllArgsConstructor
public class DiscountMapperImplementation implements DiscountMapper {

    private final DiscountRepository discountRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @Override
    public DiscountDTO toDto(Discount discount) {
        if (discount == null) {
            return null;
        }

        DiscountDTO discountDTO = new DiscountDTO();

        discountDTO.setId(discount.getId());
        discountDTO.setClient(discount.getClient().getId());
        discountDTO.setPercent(discount.getPercent());

        return discountDTO;
    }

    @Override
    public Discount fromDto(DiscountDTO discountDTO) {
        if (discountDTO == null) {
            return null;
        }

        Discount discount = new Discount();

        discount.setId(discountDTO.getId());
        discount.setClient(userRepository.findById( discountDTO.getClient()).orElseThrow(() -> new EntityNotFoundException("Client NOT FOUND!!")));

        discount.setPercent(discountDTO.getPercent());

        return discount;
    }
}