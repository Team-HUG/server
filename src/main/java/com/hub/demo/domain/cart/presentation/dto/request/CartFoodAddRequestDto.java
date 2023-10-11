package com.hub.demo.domain.cart.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartFoodAddRequestDto {
    private Long foodId;
    private int quantity;
}
