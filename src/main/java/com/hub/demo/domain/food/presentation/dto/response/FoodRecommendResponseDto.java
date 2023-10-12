package com.hub.demo.domain.food.presentation.dto.response;

import com.hub.demo.domain.food.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodRecommendResponseDto {
    private Long Id;

    private String category;

    private String foodName;

    private int price;

    private String imageUrl;

    private Boolean isEvent;

    public FoodRecommendResponseDto(Food food) {
        this.Id = food.getId();
        this.isEvent = food.getIsEvent();
        this.category = food.getCategory();
        this.foodName = food.getFoodName();
        this.price = food.getPrice();
        this.imageUrl = food.getImageUrl();
    }
}
