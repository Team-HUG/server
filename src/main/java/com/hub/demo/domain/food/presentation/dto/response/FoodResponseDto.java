package com.hub.demo.domain.food.presentation.dto.response;

import com.hub.demo.domain.food.domain.Food;
import com.hub.demo.global.util.StringFormat;
import lombok.Getter;

@Getter
public class FoodResponseDto {
    private Long Id;

    private String category;

    private String foodName;

    private String price;

    private String imageUrl;

    private Boolean isEvent;

    public FoodResponseDto(Food food) {
        StringFormat stringFormat = new StringFormat();
        this.Id = food.getId();
        this.isEvent = food.getIsEvent();
        this.category = food.getCategory();
        this.foodName = food.getFoodName();
        this.price = stringFormat.wonFormat(food.getPrice()) + "원";
        this.imageUrl = food.getImageUrl();
    }
}
