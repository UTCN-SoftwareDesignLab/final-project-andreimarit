package com.lab4.demo.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDTO {

    private Long id;
    private Long client;
    private List<ProductDTO> products;

}
