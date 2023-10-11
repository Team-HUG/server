package com.hub.demo.domain.food.service;

import com.hub.demo.domain.food.domain.Food;
import com.hub.demo.domain.food.domain.repository.FoodRepository;
import com.hub.demo.domain.food.presentation.dto.request.CreateRequestDto;
import com.hub.demo.domain.food.presentation.dto.response.FoodListResponseDto;
import com.hub.demo.global.util.FoodListBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final FoodListBuilder foodListBuilder;

    public void create(CreateRequestDto requestDto) {
        Food food = requestDto.toEntity(requestDto);

        foodRepository.save(food);
    }

    public FoodListResponseDto getFoodByCategory(String category, int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);

        return foodListBuilder.foodBuilder(foodRepository.findByCategory(pageable, category));
    }
}
