package com.hub.demo.domain.food.service;

import com.hub.demo.domain.food.domain.Food;
import com.hub.demo.domain.food.domain.repository.FoodRepository;
import com.hub.demo.domain.food.presentation.dto.request.CreateRequestDto;
import com.hub.demo.domain.food.presentation.dto.response.FoodCategory;
import com.hub.demo.domain.food.presentation.dto.response.FoodListResponseDto;
import com.hub.demo.global.s3.S3Uploader;
import com.hub.demo.global.util.FoodListBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
}
