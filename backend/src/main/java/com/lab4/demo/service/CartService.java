package com.lab4.demo.service;

import com.lab4.demo.dto.CartDTO;
import com.lab4.demo.dto.DiscountDTO;
import com.lab4.demo.dto.ProductDTO;
import com.lab4.demo.model.Cart;
import com.lab4.demo.model.Discount;
import com.lab4.demo.model.Product;
import com.lab4.demo.model.User;
import com.lab4.demo.repo.CartRepository;
import com.lab4.demo.repo.DiscountRepository;
import com.lab4.demo.repo.ProductRepository;
import com.lab4.demo.repo.UserRepository;
import com.lab4.demo.service.mapper.CartMapper;
import com.lab4.demo.service.mapper.ProductMapper;
import com.lab4.demo.websocket.WsServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {


    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;
    private final DiscountService discountService;
    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    public List<CartDTO> findAll() {
        return cartRepository.findAll().stream()
                .map(cartMapper::toDto)
                .collect(Collectors.toList());
    }

    private Cart findById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found: " + id));
    }

    public List<CartDTO> findByClientId(Long clientId){
        List<CartDTO> cartDTOList = cartRepository.findAll().stream()
                .map(cartMapper::toDto)
                .collect(Collectors.toList());

        List<CartDTO> cartDTOListClient = new ArrayList<>();

        for (CartDTO cartDTO : cartDTOList){
            if (clientId == cartDTO.getClient()) cartDTOListClient.add(cartDTO);
        }

        return cartDTOListClient;
    }



    public CartDTO create(CartDTO cart, Long percent) {

        Discount discout = discountRepository.findDiscountByPercentLike(percent);

        System.out.println("has entered in Cart Create");
        User user = userRepository.findById(cart.getClient()).orElseThrow(() -> new EntityNotFoundException("Client NOT FOUND!!"));
        System.out.println(percent);
        Long newWallet= user.getWallet();
        System.out.println("NewWallet=" + newWallet);
        Long totalPrice = 0L;
        for (ProductDTO productDTO : cart.getProducts()){
            Product product = productRepository.findById(productDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Product Not Found"));

            Long newQuantity = product.getQuantity();
            newQuantity--;
            System.out.println("Quantity= " + newQuantity);
            product.setQuantity(newQuantity);

            productRepository.save(product);
            totalPrice += product.getPrice();
        }
        Float fpercent = Float.valueOf(percent.toString());
        System.out.println("Float=" + fpercent);
        fpercent = fpercent / 100.0f;
        System.out.println("Float=" + fpercent);
        totalPrice = totalPrice - (long) (totalPrice * fpercent);
        System.out.println("totalPrice = " + totalPrice);
        newWallet -=totalPrice;
        System.out.println("NewWallet=" + newWallet);
        user.setWallet(newWallet);
        userRepository.save(user);
        discountService.delete(discout.getId());
        WsServerEndpoint.sendAlert("Purchase successful!");
        return cartMapper.toDto(cartRepository.save(
                cartMapper.fromDto(cart)
        ));
    }

    public CartDTO edit(CartDTO cart, Long id) {
        Cart actCart = findById(id);
        actCart.setId(cart.getId());
        User client = userRepository.findById( cart.getClient()).orElseThrow(() -> new EntityNotFoundException("Client NOT FOUND!!"));
        actCart.setClient(client);
        List<Product> products = cart.getProducts().stream().map(productMapper::fromDto).collect(Collectors.toList());
        actCart.setProducts(products);
        return cartMapper.toDto(
                cartRepository.save(actCart)
        );
    }

    public void delete(Long id){
        cartRepository.deleteById(id);
    }
}


