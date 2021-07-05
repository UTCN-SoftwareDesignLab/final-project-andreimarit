package com.lab4.demo.service.mapper;


import com.lab4.demo.dto.CartDTO;
import com.lab4.demo.dto.ProductDTO;
import com.lab4.demo.model.*;
import com.lab4.demo.model.Cart;
import com.lab4.demo.repo.CartRepository;
import com.lab4.demo.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CartMapperImplementation implements CartMapper {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @Override
    public CartDTO toDto(Cart cart) {
        if (cart == null) {
            return null;
        }

        CartDTO cartDTO = new CartDTO();

        cartDTO.setId(cart.getId());
        cartDTO.setClient(cart.getClient().getId());

        List<ProductDTO> products = cart.getProducts().stream().map(productMapper::toDto).collect(Collectors.toList());
        cartDTO.setProducts(products);

        return cartDTO;
    }

    @Override
    public Cart fromDto(CartDTO cartDTO) {
        if (cartDTO == null) {
            return null;
        }

        Cart cart = new Cart();

        cart.setId(cartDTO.getId());
        cart.setClient(userRepository.findById( cartDTO.getClient()).orElseThrow(() -> new EntityNotFoundException("Client NOT FOUND!!")));

        List<Product> products = cartDTO.getProducts().stream().map(productMapper::fromDto).collect(Collectors.toList());
        cart.setProducts(products);

        return cart;
    }
}