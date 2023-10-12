package com.hub.demo.domain.cart.service;

import com.hub.demo.domain.cart.domain.Cart;
import com.hub.demo.domain.cart.domain.CartItem;
import com.hub.demo.domain.cart.domain.repository.CartItemRepository;
import com.hub.demo.domain.cart.domain.repository.CartRepository;
import com.hub.demo.domain.cart.exception.CartFoodIsEmptyException;
import com.hub.demo.domain.cart.exception.CartFoodNotFoundException;
import com.hub.demo.domain.cart.presentation.dto.request.CartFoodAddRequestDto;
import com.hub.demo.domain.cart.presentation.dto.response.CartListResponseDto;
import com.hub.demo.domain.food.domain.Food;
import com.hub.demo.domain.food.domain.repository.FoodRepository;
import com.hub.demo.domain.food.exception.FoodNotFoundException;
import com.hub.demo.domain.order.domain.Order;
import com.hub.demo.domain.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public void addFood(CartFoodAddRequestDto requestDto) {
        Cart cart = cartRepository.findByTableNumber(1);

        Food food = foodRepository.findById(requestDto.getFoodId())
                .orElseThrow(() -> FoodNotFoundException.EXCEPTION);

        if (cartItemRepository.existsByFoodAndIsOrder(food, false) &&
                cartItemRepository.existsByCartAndIsOrder(cart, false))  {
            CartItem cartItem = cartItemRepository.findByCartAndFoodAndIsOrder(cart, food, false);
            cartItem.changeQuantity(requestDto.getQuantity());
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = new CartItem(cart, food, requestDto.getQuantity());
            cartItemRepository.save(cartItem);
        }

    }

    @Transactional(readOnly = true)
    public List<CartListResponseDto> getCartFoodList() {
        Cart cart = cartRepository.findByTableNumber(1);

        List<CartItem> cartItems = cartItemRepository.findAllByCartAndIsOrder(cart, false);

        return cartItems.stream().map(it -> new CartListResponseDto(it.getFood(), it)).toList();
    }

    @Transactional
    public void deleteCartFood(Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> CartFoodNotFoundException.EXCEPTION);

        cartItemRepository.delete(cartItem);
    }

    public boolean submitOrder() {
        Cart cart = cartRepository.findByTableNumber(1);
        AtomicBoolean isEvent = new AtomicBoolean(false);

        List<CartItem> cartItems = cartItemRepository.findAllByCartAndIsOrder(cart, false);

        if (cartItems.isEmpty()) {
            throw CartFoodIsEmptyException.EXCEPTION;
        }

        cartItems.forEach(cartItem -> {
            if (cartItem.getFood().getIsEvent() == true) {
                isEvent.set(true);
            }
        });

        cartItems.forEach(CartItem::submitOrder);
        Order order = new Order(cartItems);
        cartItemRepository.saveAll(cartItems);
        orderRepository.save(order);

        return isEvent.get();
    }

    @Transactional
    public void deleteCartItem() {
        Cart cart = cartRepository.findByTableNumber(1);
        List<CartItem> cartItems = cartItemRepository.findAllByCartAndIsOrder(cart, false);

        cartItemRepository.deleteAll(cartItems);
    }
}
