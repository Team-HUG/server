package com.hub.demo.domain.food.presentation.dto.response;

import com.hub.demo.domain.food.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class FoodResponseDto {
    private String category;

    private String foodName;

    private String price;

    private String image;

    public FoodResponseDto(Food food) {
        this.category = food.getCategory();
        this.foodName = food.getFoodName();
        this.price = food.getPrice();
        this.image = food.getImage();
    }
}
