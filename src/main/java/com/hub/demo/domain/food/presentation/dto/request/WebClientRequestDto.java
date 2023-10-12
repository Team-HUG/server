package com.hub.demo.domain.food.presentation.dto.request;

import com.hub.demo.domain.food.domain.Food;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebClientRequestDto {
    private List<String> foods = new ArrayList<>();

    private String taste;

    private String texture;

    private String staple;

    private String temperature;

    public WebClientRequestDto(RecommendRequestDto requestDto) {
        this.taste = requestDto.getTaste();
        this.texture = requestDto.getTexture();
        this.staple = requestDto.getStaple();
        this.temperature = requestDto.getTemperature();
    }

    public void injectFoodList(List<Food> foodList) {
        foodList.forEach(food -> foods.add(food.getFoodName()));
    }

}
