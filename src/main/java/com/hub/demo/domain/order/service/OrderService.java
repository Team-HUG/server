package com.hub.demo.domain.order.service;

import com.hub.demo.domain.cart.domain.Cart;
import com.hub.demo.domain.cart.domain.repository.CartRepository;
import com.hub.demo.domain.order.domain.Order;
import com.hub.demo.domain.order.domain.repository.OrderRepository;
import com.hub.demo.domain.order.persentation.dto.response.OrderListResponseDto;
import com.hub.demo.global.util.FoodListBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final FoodListBuilder foodListBuilder;

    public List<OrderListResponseDto> getOrderList() {
        Cart cart = cartRepository.findByTableNumber(1);

        List<Order> order = orderRepository.findByOrderItems_Cart(cart);

        return foodListBuilder.orderBuilder(order);
    }

    public void isComplete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow();

        order.changeIsComplete();
    }
}
