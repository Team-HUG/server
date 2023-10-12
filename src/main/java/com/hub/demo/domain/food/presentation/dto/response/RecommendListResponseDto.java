package com.hub.demo.domain.food.presentation.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendListResponseDto {
    private List<RecommendResponseDto> recommendations;
}
