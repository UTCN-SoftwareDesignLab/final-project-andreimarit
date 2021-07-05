package com.lab4.demo.controller;

import com.lab4.demo.dto.CartDTO;
import com.lab4.demo.dto.DiscountDTO;
import com.lab4.demo.service.CartService;
import com.lab4.demo.service.DiscountService;
import com.lab4.demo.util.report.ReportServiceFactory;
import com.lab4.demo.util.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(CART)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final DiscountService discountService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<CartDTO> allCarts() {
        return cartService.findAll();
    }

    @GetMapping(ID)
    public List<CartDTO> allCartsClient(@PathVariable Long id){return cartService.findByClientId(id);}

    @PostMapping(ID)
    public CartDTO create(@RequestBody CartDTO cart, @PathVariable Long id) {
        return cartService.create(cart, id);
    }

    @PatchMapping(ID)
    public CartDTO edit(@RequestBody CartDTO cart, @PathVariable Long id) {
        return cartService.edit(cart, id);
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id){
        cartService.delete(id);
    }


}
