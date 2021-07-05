package com.lab4.demo.service.mapper;

        import com.lab4.demo.dto.CartDTO;
        import com.lab4.demo.model.Cart;

//@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDTO toDto(Cart Cart);

    Cart fromDto(CartDTO cart);

}
