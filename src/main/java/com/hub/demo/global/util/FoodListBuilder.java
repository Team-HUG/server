package com.hub.demo.global.util;

import com.hub.demo.domain.food.domain.Food;
import com.hub.demo.domain.food.presentation.dto.response.FoodListResponseDto;
import com.hub.demo.domain.food.presentation.dto.response.FoodResponseDto;
import com.hub.demo.domain.order.domain.Order;
import com.hub.demo.domain.order.persentation.dto.response.OrderListResponseDto;
import com.hub.demo.domain.order.persentation.dto.response.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FoodListBuilder {
    private final TimeAgoFormatter timeAgoFormatter;

    public FoodListResponseDto foodBuilder(Page<Food> food) {
        return FoodListResponseDto.builder()
                .hasMorePage(food.hasNext())
                .currentPage(food.getNumber() + 1)
                .responseDtoList(food.stream()
                        .map(FoodResponseDto::new).toList())
                .build();
    }

    public List<OrderListResponseDto> orderBuilder(List<Order> orders) {
        return orders.stream()
                .map(it -> OrderListResponseDto.builder()
                        .id(it.getId())
                        .responseDtoList(it.getOrderItems().stream()
                                .map(OrderResponseDto::new).toList())
                        .orderTime(timeAgoFormatter.format(it.getCreateDate()))
                        .tableNumber(1)
                        .build())
                .collect(Collectors.toList());
    }
}
