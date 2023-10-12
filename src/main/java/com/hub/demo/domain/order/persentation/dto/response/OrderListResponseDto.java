package com.hub.demo.domain.order.persentation.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OrderListResponseDto {
    private Long id;

    private List<OrderResponseDto> responseDtoList;

    private int tableNumber;

    private boolean isComplete;

    private String orderTime;
}
