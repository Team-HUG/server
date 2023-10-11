package com.hub.demo.domain.food.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FoodListResponseDto {
    private List<FoodResponseDto> responseDtoList;

    private boolean hasMorePage;

    private int currentPage;
}
