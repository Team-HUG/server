package com.hub.demo.domain.food.presentation;

import com.hub.demo.domain.food.presentation.dto.request.CreateRequestDto;
import com.hub.demo.domain.food.presentation.dto.request.RecommendRequestDto;
import com.hub.demo.domain.food.presentation.dto.response.FoodCategory;
import com.hub.demo.domain.food.presentation.dto.response.FoodDetailResponseDto;
import com.hub.demo.domain.food.presentation.dto.response.FoodListResponseDto;
import com.hub.demo.domain.food.presentation.dto.response.FoodRecommendResponseDto;
import com.hub.demo.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void foodCreate(@RequestPart(value = "data") CreateRequestDto requestDto,
                           @RequestPart(value = "file") MultipartFile multipartFile) {
        foodService.create(requestDto, multipartFile);
    }

    @GetMapping("/list")
    public FoodListResponseDto getFoodByCategory(@RequestParam(value = "category") String category,
                                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "limit", defaultValue = "10") int limit) {
        return foodService.getFoodByCategory(category, page, limit);
    }

    @GetMapping("/detail/{id}")
    public FoodDetailResponseDto getDetailFood(@PathVariable(name = "id") Long id) {
        return foodService.getDetailFood(id);
    }

    @GetMapping("/category/list")
    public FoodCategory getCategoryList() {
        return foodService.getCategory();
    }

    @PatchMapping("/change/event/{id}")
    public void changeEvent(@PathVariable(name = "id") Long id) {
        foodService.changeEvent(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteFood(@PathVariable(name = "id") Long id) {
        foodService.deleteFood(id);
    }

    @PostMapping("/recommend/food")
    public List<FoodRecommendResponseDto> getRecommendFood(@RequestBody RecommendRequestDto requestDto) {
        return foodService.getFood(requestDto);
    }
}
