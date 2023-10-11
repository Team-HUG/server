package com.hub.demo.domain.food.presentation.dto.response;

import com.hub.demo.domain.food.domain.Food;
import lombok.Getter;

@Getter
public class FoodResponseDto {
    private String category;

    private String foodName;

    private String price;

    private String imageUrl;

    private Boolean isEvent;

    public FoodResponseDto(Food food) {
        this.isEvent = food.getIsEvent();
        this.category = food.getCategory();
        this.foodName = food.getFoodName();
        this.price = food.getPrice();
        this.imageUrl = food.getImageUrl();
    }
}
