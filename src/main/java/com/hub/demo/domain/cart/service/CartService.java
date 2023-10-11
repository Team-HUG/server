package com.hub.demo.domain.cart.service;

import com.hub.demo.domain.cart.domain.Cart;
import com.hub.demo.domain.cart.domain.CartItem;
import com.hub.demo.domain.cart.domain.repository.CartItemRepository;
import com.hub.demo.domain.cart.domain.repository.CartRepository;
import com.hub.demo.domain.cart.exception.CartFoodNotFoundException;
import com.hub.demo.domain.cart.presentation.dto.request.CartFoodAddRequestDto;
import com.hub.demo.domain.cart.presentation.dto.response.CartListResponseDto;
import com.hub.demo.domain.food.domain.Food;
import com.hub.demo.domain.food.domain.repository.FoodRepository;
import com.hub.demo.domain.food.exception.FoodNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public void addFood(CartFoodAddRequestDto requestDto) {
        Cart cart = cartRepository.findByTableNumber(1);

        Food food = foodRepository.findById(requestDto.getFoodId())
                .orElseThrow(() -> FoodNotFoundException.EXCEPTION);

        if (!cartItemRepository.existsByFood(food) &&
                cartItemRepository.existsByCart(cart))  {
            CartItem cartItem = new CartItem(cart, food, requestDto.getQuantity());
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = cartItemRepository.findByCartAndFood(cart, food);
            cartItem.changeQuantity(requestDto.getQuantity());
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

    public void submitOrder() {
        Cart cart = cartRepository.findByTableNumber(1);

        List<CartItem> cartItems = cartItemRepository.findAllByCartAndIsOrder(cart, false);

        cartItems.forEach(it -> {
            it.changeIsOrder();
            it.submitOrderTime();
        });
        cartItemRepository.saveAll(cartItems);
    }
}
