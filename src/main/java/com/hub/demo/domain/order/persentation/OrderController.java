package com.hub.demo.domain.order.persentation;

import com.hub.demo.domain.order.persentation.dto.response.OrderListResponseDto;
import com.hub.demo.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderListResponseDto> getOrderList() {
        return orderService.getOrderList();
    }

    @PatchMapping("/change/complete/{id}")
    public void changeIsComplete(@PathVariable Long id) {
        orderService.isComplete(id);
    }
}
