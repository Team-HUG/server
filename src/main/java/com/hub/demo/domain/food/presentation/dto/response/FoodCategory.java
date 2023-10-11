package com.hub.demo.domain.food.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FoodCategory {
    private List<String> category;
}
