package com.hub.demo.domain.food.presentation;

import com.hub.demo.domain.food.presentation.dto.request.CreateRequestDto;
import com.hub.demo.domain.food.presentation.dto.response.FoodListResponseDto;
import com.hub.demo.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void foodCreate(@RequestBody CreateRequestDto requestDto) {
        foodService.create(requestDto);
    }

    @GetMapping("/getFood")
    public FoodListResponseDto getFoodByCategory(@RequestParam(value = "category") String category,
                                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "limit", defaultValue = "10") int limit) {
        return foodService.getFoodByCategory(category, page, limit);
    }
}