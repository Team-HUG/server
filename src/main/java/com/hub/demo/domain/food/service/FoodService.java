package com.hub.demo.domain.food.service;

import com.hub.demo.domain.food.domain.Food;
import com.hub.demo.domain.food.domain.repository.FoodRepository;
import com.hub.demo.domain.food.exception.FoodNotFoundException;
import com.hub.demo.domain.food.presentation.dto.request.CreateRequestDto;
import com.hub.demo.domain.food.presentation.dto.request.RecommendRequestDto;
import com.hub.demo.domain.food.presentation.dto.request.WebClientRequestDto;
import com.hub.demo.domain.food.presentation.dto.response.*;
import com.hub.demo.global.s3.S3Uploader;
import com.hub.demo.global.util.FoodListBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final S3Uploader s3Uploader;
    private final FoodRepository foodRepository;
    private final FoodListBuilder foodListBuilder;

    public void create(CreateRequestDto requestDto, MultipartFile file) {
        Food food = requestDto.toEntity(requestDto);

        food.injectImage(s3Uploader.uploadImage(file));

        foodRepository.save(food);
    }

    public FoodListResponseDto getFoodByCategory(String category, int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);

        return foodListBuilder.foodBuilder(foodRepository.findByCategory(pageable, category));
    }

    public FoodCategory getCategory() {
        List<String> category = foodRepository.findDistinctByCategory();

        return new FoodCategory(category);
    }

    @Transactional
    public void changeEvent(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> FoodNotFoundException.EXCEPTION);

        food.changeEvent();
    }

    @Transactional(readOnly = true)
    public FoodDetailResponseDto getDetailFood(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> FoodNotFoundException.EXCEPTION);

        return new FoodDetailResponseDto(food);
    }

    @Transactional
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    @Transactional
    public List<FoodRecommendResponseDto> getFood(RecommendRequestDto requestDto) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://127.0.0.1:8000")
                .build();

        List<Food> foodList = foodRepository.findAll();

        WebClientRequestDto webClientRequestDto = new WebClientRequestDto(requestDto);

        webClientRequestDto.injectFoodList(foodList);

        RecommendListResponseDto responseDto = webClient.post()
                .uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(webClientRequestDto)
                .retrieve()
                .bodyToMono(RecommendListResponseDto.class)
                .block();

        List<Food> food = responseDto.getRecommendations().stream()
                .map(it -> foodRepository.findByFoodName(it.getFood())
                    .orElseThrow(() -> FoodNotFoundException.EXCEPTION))
                .toList();

        return food.stream().map(FoodRecommendResponseDto::new)
                .toList();
    }
}
