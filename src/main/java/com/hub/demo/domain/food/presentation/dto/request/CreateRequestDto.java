package com.hub.demo.domain.food.presentation.dto.request;

import com.hub.demo.domain.food.domain.Food;
import com.hub.demo.global.util.StringFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRequestDto {
    private String category;

    private String foodName;

    private int price;

    private String content;

    public Food toEntity(CreateRequestDto requestDto) {
        StringFormat stringFormat = new StringFormat();
        return Food.builder()
                .category(requestDto.category)
                .foodName(requestDto.foodName)
                .price(requestDto.price)
                .content(requestDto.content)
                .build();
    }
}