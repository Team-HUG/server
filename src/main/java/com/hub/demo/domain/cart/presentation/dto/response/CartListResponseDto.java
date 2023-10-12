package com.hub.demo.domain.cart.presentation.dto.response;

import com.hub.demo.domain.cart.domain.CartItem;
import com.hub.demo.domain.food.domain.Food;
import lombok.Getter;

@Getter
public class CartListResponseDto {
    private Long cartId;

    private Long foodId;

    private String foodName;

    private int price;

    private Boolean isEvent;

    private String imageUrl;

    private int quantity;

    public CartListResponseDto(Food food, CartItem cartItem) {
        this.foodName = food.getFoodName();
        this.price = food.getPrice();
        this.isEvent = food.getIsEvent();
        this.imageUrl = food.getImageUrl();
        this.quantity = cartItem.getQuantity();
        this.cartId = cartItem.getId();
        this.foodId = food.getId();
    }
}
