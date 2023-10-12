package com.hub.demo.domain.food.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendRequestDto {

    private String taste;

    private String texture;

    private String staple;

    private String temperature;
}
