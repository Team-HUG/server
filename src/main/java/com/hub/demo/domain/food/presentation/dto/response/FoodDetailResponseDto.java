package com.hub.demo.domain.food.presentation.dto.response;

import com.hub.demo.domain.food.domain.Food;
import com.hub.demo.global.util.StringFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodDetailResponseDto {

    private Long Id;

    private String category;

    private String foodName;

    private int price;

    private String imageUrl;

    private Boolean isEvent;

    private String content;

    public FoodDetailResponseDto(Food food) {
        StringFormat stringFormat = new StringFormat();
        this.Id = food.getId();
        this.isEvent = food.getIsEvent();
        this.category = food.getCategory();
        this.foodName = food.getFoodName();
        this.price = food.getPrice();
        this.imageUrl = food.getImageUrl();
        this.content = food.getContent();
    }
}
