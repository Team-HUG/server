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

    private String price;

    private String image;

    public Food toEntity(CreateRequestDto requestDto) {
        return Food.builder()
                .category(requestDto.category)
                .foodName(requestDto.foodName)
                .price(requestDto.price)
                .image(requestDto.image)
                .build();
    }
}