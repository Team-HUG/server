package com.hub.demo.domain.order.persentation.dto.response;

import com.hub.demo.domain.cart.domain.CartItem;
import com.hub.demo.domain.food.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponseDto {

    private String foodName;

    private int price;

    private int quantity;

    public OrderResponseDto(CartItem cartItem) {
        this.foodName = cartItem.getFood().getFoodName();
        this.price = cartItem.getFood().getPrice();
        this.quantity = cartItem.getQuantity();
    }
}
