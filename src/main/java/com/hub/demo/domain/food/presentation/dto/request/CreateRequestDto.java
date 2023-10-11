package com.hub.demo.domain.food.presentation.dto.request;

import com.hub.demo.domain.food.domain.Food;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRequestDto {
    private String category;

    private String foodName;

    private int price;


    public Food toEntity(CreateRequestDto requestDto) {
        return Food.builder()
                .category(requestDto.category)
                .foodName(requestDto.foodName)
                .price(requestDto.price)
                .build();
    }
}