package com.lab4.demo.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiscountDTO {

    private Long id;
    private Long client;
    private Long percent;
}
