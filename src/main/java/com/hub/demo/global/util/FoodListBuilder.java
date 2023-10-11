package com.hub.demo.global.util;

import com.hub.demo.domain.food.domain.Food;
import com.hub.demo.domain.food.domain.repository.FoodRepository;
import com.hub.demo.domain.food.presentation.dto.response.FoodListResponseDto;
import com.hub.demo.domain.food.presentation.dto.response.FoodResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FoodListBuilder {
    private final FoodRepository foodRepository;

    public FoodListResponseDto foodBuilder(Page<Food> food) {
        return FoodListResponseDto.builder()
                .hasMorePage(food.hasNext())
                .currentPage(food.getNumber() + 1)
                .responseDtoList(food.stream()
                        .map(FoodResponseDto::new).toList())
                .build();
    }
}
